package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.common.api.Flight;
import io.codelex.flightplanner.common.api.PageResult;
import io.codelex.flightplanner.customer.api.SearchFlightsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
class CustomerFlightController {
    private final CustomerFlightService service;

    @PostMapping("/search")
    public PageResult<Flight> searchFlights(@Valid @RequestBody SearchFlightsRequest req,
                                            @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                            @RequestParam(value = "limit", defaultValue = "25") Integer limit) {
        return service.searchFlights(req, offset, limit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> fetchFlight(@PathVariable("id") Long id) {
        return service.fetchFlight(id)
                .map(it -> new ResponseEntity<>(it, OK))
                .orElseGet(() -> notFound().build());
    }
}
