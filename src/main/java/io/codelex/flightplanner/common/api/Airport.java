package io.codelex.flightplanner.common.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class Airport {
    @NotEmpty
    private String airport;
    @NotEmpty
    private String country;
    @NotEmpty
    private String city;

    public Airport(@JsonProperty("airport") String airport,
                   @JsonProperty("country") String country,
                   @JsonProperty("city") String city) {
        this.airport = airport != null ? airport.toUpperCase().trim() : null;
        this.country = country != null ? country.trim() : null;
        this.city = city != null ? city.trim() : null;
    }
}
