package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.common.api.Airport;
import io.codelex.flightplanner.repository.model.AirportRecord;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AirportRecordToAirport implements Function<AirportRecord, Airport> {
    @Override
    public Airport apply(AirportRecord source) {
        return Airport.builder()
                .airport(source.getAirport())
                .country(source.getCountry())
                .city(source.getCity())
                .build();
    }
}
