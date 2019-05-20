package io.codelex.flightplanner.customer.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class SearchFlightsRequest {
    @NotEmpty
    private final String from;
    @NotEmpty
    private final String to;
    @NotNull
    private final LocalDate departureDate;

    public SearchFlightsRequest(@JsonProperty("from") String from,
                                @JsonProperty("to") String to,
                                @JsonProperty("departureDate") LocalDate departureDate) {
        this.from = from != null ? from.trim().toUpperCase() : null;
        this.to = to != null ? to.trim().toUpperCase() : null;
        this.departureDate = departureDate;
    }
}
