package entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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

    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }

    public String getIsoName() {
        return isoName;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;

        Currency currency = (Currency) o;
        return id == currency.id && Objects.equals(fullName, currency.fullName) && Objects.equals(isoName, currency.isoName);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(fullName);
        result = 31 * result + Objects.hashCode(isoName);
        return result;
    }
}
