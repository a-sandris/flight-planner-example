package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.admin.api.AddFlightRequest;
import io.codelex.flightplanner.common.api.Airport;
import io.codelex.flightplanner.common.api.Flight;
import io.codelex.flightplanner.repository.AirportRepository;
import io.codelex.flightplanner.repository.FlightRecordToFlight;
import io.codelex.flightplanner.repository.FlightRepository;
import io.codelex.flightplanner.repository.model.FlightRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class AdminFlightsService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final FlightRecordToFlight toFlight;

    @Transactional
    public Flight addFlight(AddFlightRequest req) {
        var from = req.getFrom();
        var to = req.getTo();
        if (from.getAirport().equals(to.getAirport())) {
            throw new IllegalArgumentException();
        }
        if (!isArrivalAndDepartureValid(req)) {
            throw new IllegalArgumentException();
        }
        if (flightExists(req, from, to)) {
            throw new IllegalStateException();
        }
        var flight = FlightRecord.builder()
                .from(airportRepository.findOrCreate(from.getAirport(), from.getCountry(), from.getCity()))
                .to(airportRepository.findOrCreate(to.getAirport(), to.getCountry(), to.getCity()))
                .departureTime(req.getDepartureTime())
                .arrivalTime(req.getArrivalTime())
                .carrier(req.getCarrier())
                .build();
        return toFlight.apply(flightRepository.save(flight));
    }

    private boolean isArrivalAndDepartureValid(AddFlightRequest req) {
        return req.getArrivalTime().isAfter(req.getDepartureTime());
    }

    private boolean flightExists(AddFlightRequest req, Airport from, Airport to) {
        return flightRepository.flightExists(
                from.getAirport(),
                to.getAirport(),
                req.getCarrier(),
                req.getDepartureTime(),
                req.getArrivalTime()
        );
    }

    @Transactional(readOnly = true)
    public Optional<Flight> fetchFlight(Long id) {
        return flightRepository.findById(id).map(toFlight);
    }

    @Transactional
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}
