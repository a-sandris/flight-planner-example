package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.common.api.Airport;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
class CustomerAirportController {
    private final CustomerAirportService service;

    @GetMapping
    public List<Airport> fetchAirports(@RequestParam("search") String search) {
        return service.fetchAirports(search);
    }
}
