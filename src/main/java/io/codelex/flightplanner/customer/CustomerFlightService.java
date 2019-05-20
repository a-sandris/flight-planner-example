package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.common.api.Flight;
import io.codelex.flightplanner.common.api.PageResult;
import io.codelex.flightplanner.customer.api.SearchFlightsRequest;
import io.codelex.flightplanner.repository.FlightRecordToFlight;
import io.codelex.flightplanner.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class CustomerFlightService {
    private final FlightRepository flightRepository;
    private final FlightRecordToFlight toFlight;

    @Transactional(readOnly = true)
    public PageResult<Flight> searchFlights(SearchFlightsRequest req, Integer offset, Integer limit) {
        if (req.getFrom().equals(req.getTo())) {
            throw new IllegalArgumentException();
        }
        var pageRequest = PageRequest.of(offset / limit, limit);
        var page = flightRepository.findFlights(req.getFrom(), req.getTo(), req.getDepartureDate(), pageRequest).map(toFlight);
        return PageResult.fromPage(page);
    }

    @Transactional(readOnly = true)
    public Optional<Flight> fetchFlight(Long id) {
        return flightRepository.findById(id).map(toFlight);
    }
}
