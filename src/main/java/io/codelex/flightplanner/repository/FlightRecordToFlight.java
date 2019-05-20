package io.codelex.flightplanner.repository;

import io.codelex.flightplanner.common.api.Flight;
import io.codelex.flightplanner.repository.model.FlightRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class FlightRecordToFlight implements Function<FlightRecord, Flight> {
    private final AirportRecordToAirport toAirport;

    @Override
    public Flight apply(FlightRecord source) {
        return Flight.builder()
                .id(source.getId())
                .from(toAirport.apply(source.getFrom()))
                .to(toAirport.apply(source.getTo()))
                .carrier(source.getCarrier())
                .departureTime(source.getDepartureTime())
                .arrivalTime(source.getArrivalTime())
                .build();
    }
}
