package repository;

import dto.Country;
import dto.Plane;
import dto.PlaneType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlaneDBRepository implements Repository<Plane> {

    private Connection connection;

    public PlaneDBRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Plane obj) throws IOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.plane(plane_type_id, country_registration_id, local_number, international_number, count_flight, create_date, last_date) VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, Objects.requireNonNull(obj.getPlaneType().getId()));
            preparedStatement.setInt(2, Objects.requireNonNull(obj.getCountryRegistration().getId()));
            preparedStatement.setString(3, Objects.requireNonNull(obj.getLocalNumber()));
            preparedStatement.setString(4, Objects.requireNonNull(obj.getInternationNumber()));
            preparedStatement.setInt(5, Objects.requireNonNull(obj.getCountFlight()));
            preparedStatement.setDate(6, Date.valueOf(Objects.requireNonNull(obj.getCreateDate())));
            preparedStatement.setDate(7, Date.valueOf(Objects.requireNonNull(obj.getLastDate())));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IOException("Can not save Plane", e);
        }
    }

    @Override
    public Plane load(int id) throws IOException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.plane WHERE id = ?");
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Plane(
                            resultSet,
                            loadPlaneType(resultSet.getInt("place_type_id")),
                            loadCountry(resultSet.getInt("country_registration_id"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Plane> load(List<Integer> ids) {
        return ids.stream().map(id -> {
            try {
                return load(id);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<Plane> loadAll() throws IOException {
        try (ResultSet resultSet = connection.prepareStatement("SELECT * FROM public.plane").executeQuery()) {
            List<Plane> planes = new ArrayList<>();
            while (resultSet.next()) {
                Plane plane = new Plane(
                        resultSet,
                        loadPlaneType(resultSet.getInt("place_type_id")),
                        loadCountry(resultSet.getInt("country_registration_id"))
                );

                planes.add(plane);
            }
            return planes;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private Country loadCountry(int id) throws SQLException {
        PreparedStatement countryStatement = connection.prepareStatement("SELECT * FROM public.country WHERE id = ?");
        countryStatement.setInt(1, id);
        try (ResultSet resultSet = countryStatement.executeQuery()) {
            if (resultSet.next()) {
                Country country = new Country();
                country.setId(resultSet.getInt("id"));
                country.setName(resultSet.getString("name"));
                country.setIsoName(resultSet.getString("iso_name"));
                return country;
            }
        }

        return null;
    }

    private PlaneType loadPlaneType(int id) throws SQLException {
        PreparedStatement placeTypeStatement = connection.prepareStatement("SELECT * FROM public.place_type WHERE id = ?");
        placeTypeStatement.setInt(1, id);
        try (ResultSet resultSet = placeTypeStatement.executeQuery()) {
            if (resultSet.next()) {
                PlaneType planeType = new PlaneType();
                planeType.setId(resultSet.getInt("id"));
                planeType.setManufacturer(resultSet.getString("manufacturer"));
                planeType.setName(resultSet.getString("name"));
                planeType.setMaxAltitude(resultSet.getBigDecimal("max_altitude"));
                planeType.setMaxSpeed(resultSet.getBigDecimal("max_speed"));
                planeType.setMaxRange(resultSet.getBigDecimal("max_range"));
                planeType.setMaxPassengers(resultSet.getInt("max_passengers"));
                return planeType;
            }
        }

        return null;
    }
}
