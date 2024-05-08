package entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

// 4
public class Currency {

    private int id;
    private String fullName;
    private String isoName;

    @JsonCreator
    public Currency(@JsonProperty("id") int id, @JsonProperty("fullName") String fullName, @JsonProperty("isoName") String isoName) {
        this.fullName = fullName;
        this.id = id;
        this.isoName = isoName;
    }

    // 4.1
    public String getFullName() {
        return fullName;
    }

    // 4.1
    public int getId() {
        return id;
    }

    // 4.1
    public String getIsoName() {
        return isoName;
    }

    // 4.2
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;

        Currency currency = (Currency) o;
        return id == currency.id && Objects.equals(fullName, currency.fullName) && Objects.equals(isoName, currency.isoName);
    }

    // 4.2
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(fullName);
        result = 31 * result + Objects.hashCode(isoName);
        return result;
    }
}
