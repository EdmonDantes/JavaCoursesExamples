import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import entity.Currency;
import entity.Sale;
import lombok.Data;
import repository.CurrencyResourceRepository;
import repository.JsonFilesRepository;
import repository.Repository;
import service.CurrencyService;
import service.SaleService;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // 9.1
        mapper.registerModules(new JavaTimeModule()); // 9.2
        HttpClient client = HttpClient.newHttpClient(); // 9.3

        CurrencyResourceRepository currencyRepository = new CurrencyResourceRepository(mapper);
        CurrencyService currencyService = new CurrencyService(client, mapper, currencyRepository);
        Repository<Sale> directorySaleRepository = new JsonFilesRepository<Sale>(new File("db/sales"), mapper, new TypeReference<>() {
        }, Sale::getId);

        SaleService saleService = new SaleService(directorySaleRepository, currencyService);

        HttpServer server = HttpServer.create(new InetSocketAddress(9090), 0); // 9.8
        HttpContext context = server.createContext("/sales"); // 9.9

        // 9.10
        context.setHandler(exchange -> {
            SaleRequest request = mapper.readValue(exchange.getRequestBody(), SaleRequest.class);
            List<Sale> sales = new ArrayList<>();
            if (request.getId() != null) {
                Sale found = directorySaleRepository.load(request.getId());
                if (found != null) {
                    sales.add(found);
                }
            }

            if (request.getIds() != null) {
                List<Sale> found = directorySaleRepository.load(request.getIds());
                if (found != null && !found.isEmpty()) {
                    sales.addAll(found);
                }
            }

            if (request.getPersonId() != null) {
                List<Sale> found = saleService.loadAllByPersonId(request.getPersonId());
                if (found != null && !found.isEmpty()) {
                    sales.addAll(found);
                }
            }

            List<Double> amount = null;
            if (request.getCurrencyId() != null) {
                Currency currency = currencyService.getById(request.getCurrencyId());
                if (currency != null) {
                    amount = sales.stream().map(x -> saleService.getAmount(x, currency)).collect(Collectors.toList());
                }
            }

            SaleResponse response = new SaleResponse();
            response.setSales(sales);
            response.setAmount(amount);

            exchange.sendResponseHeaders(200, 0);
            mapper.writeValue(exchange.getResponseBody(), response);
        });

        server.start(); // 9.11
    }

    // 9.4, 9.5
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static final class SaleRequest {
        private Integer id;
        private List<Integer> ids;
        private Integer personId;
        private Integer currencyId;
    }

    // 9.6, 9.7
    @Data
    @JsonInclude(Include.NON_EMPTY)
    private static final class SaleResponse {
        private List<Sale> sales;
        private List<Double> amount;
    }

}
