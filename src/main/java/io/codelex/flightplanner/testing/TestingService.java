package io.codelex.flightplanner.testing;

import io.codelex.flightplanner.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class TestingService {
    private final FlightRepository flightRepository;

    @Transactional
    public void clear() {
        flightRepository.deleteAllInBatch();
    }
}
