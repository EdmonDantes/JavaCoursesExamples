package dto;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Data
public class Plane {

    private Integer id;
    private PlaneType planeType;
    private Country countryRegistration;
    private String localNumber;
    private String internationNumber;
    private Integer countFlight;
    private LocalDate createDate;
    private LocalDate lastDate;

    public Plane(ResultSet resultSet, PlaneType planeType, Country countryRegistration) throws SQLException {
        setId(resultSet.getInt("id"));
        setPlaneType(planeType);
        setCountryRegistration(countryRegistration);
        setLocalNumber(resultSet.getString("local_number"));
        setInternationNumber(resultSet.getString("internation_number"));
        setCountFlight(resultSet.getInt("count_flight"));
        setCreateDate(resultSet.getDate("create_date").toLocalDate());
        setLastDate(resultSet.getDate("last_date").toLocalDate());
    }

}
