package dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlaneType {
    private Integer id;
    private String manufacturer;
    private String name;
    private BigDecimal maxAltitude;
    private BigDecimal maxSpeed;
    private BigDecimal maxRange;
    private Integer maxPassengers;
}
