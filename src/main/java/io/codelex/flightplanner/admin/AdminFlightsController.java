package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.admin.api.AddFlightRequest;
import io.codelex.flightplanner.common.api.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/admin-api/flights")
@RequiredArgsConstructor
class AdminFlightsController {
    private final AdminFlightsService service;

    @PutMapping
    @ResponseStatus(CREATED)
    public Flight addFlight(@Valid @RequestBody AddFlightRequest req) {
        return service.addFlight(req);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> fetchFlight(@PathVariable("id") Long id) {
        return service.fetchFlight(id)
                .map(it -> new ResponseEntity<>(it, OK))
                .orElseGet(() -> notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable("id") Long id) {
        service.deleteFlight(id);
    }
}
