package io.codelex.flightplanner.testing;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
@RequiredArgsConstructor
class TestingController {
    private final TestingService service;

    @PostMapping("/clear")
    public void clear() {
        service.clear();
    }
}
