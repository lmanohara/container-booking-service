package com.maersk.containerbookingservice.controller;

import com.maersk.containerbookingservice.model.AvailableSpacesResponse;
import com.maersk.containerbookingservice.model.BookingsRef;
import com.maersk.containerbookingservice.model.BookingsSpec;
import com.maersk.containerbookingservice.service.BookingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bookings")
@Slf4j
public class BookingsController {

    private final BookingsService bookingsService;

    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }


    @PostMapping("/checkAvailable")
    public Mono<AvailableSpacesResponse> checkAvailableSpaces(@RequestBody BookingsSpec bookingsSpec) {
        return bookingsService.checkAvailableSpaces(bookingsSpec);
    }

    @PostMapping("/create")
    public Mono<BookingsRef> createBookings(@RequestBody BookingsSpec bookingsSpec) {
        final String errorMessage = "Sorry there was a problem processing your request";
        return bookingsService.createBookings(bookingsSpec)
                .doOnError(error -> log.error(errorMessage, error))
                .onErrorMap(e -> new Exception(errorMessage));
    }

}
