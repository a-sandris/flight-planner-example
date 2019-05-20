package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.common.api.Airport;
import io.codelex.flightplanner.repository.AirportRecordToAirport;
import io.codelex.flightplanner.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
class CustomerAirportService {
    private final AirportRepository airportRepository;
    private final AirportRecordToAirport toAirport;

    @Transactional(readOnly = true)
    public List<Airport> fetchAirports(String search) {
        return airportRepository.search(search).stream()
                .map(toAirport)
                .collect(toList());
    }
}
