package com.maersk.containerbookingservice.service;

import com.maersk.containerbookingservice.configuration.ExternalServiceClient;
import com.maersk.containerbookingservice.model.AvailableSpacesResponse;
import com.maersk.containerbookingservice.model.BookingsRef;
import com.maersk.containerbookingservice.model.BookingsSpec;
import com.maersk.containerbookingservice.model.ExternalAPIResponse;
import com.maersk.containerbookingservice.repository.BookingsRepository;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class BookingsServiceImpl implements BookingsService {

    private final ExternalServiceClient externalServiceClient;
    private final BookingsRepository bookingsRepository;

    private int primaryKeyIncrement = 957000000;

    public BookingsServiceImpl(ExternalServiceClient externalServiceClient, BookingsRepository bookingsRepository) {
        this.externalServiceClient = externalServiceClient;
        this.bookingsRepository = bookingsRepository;
    }

    @Override
    public Mono<AvailableSpacesResponse> checkAvailableSpaces(@NonNull BookingsSpec bookingsSpec) {
        ExternalAPIResponse externalAPIResponse = externalServiceClient.callExternalAPI("/checkAvailable");

        Boolean availability = Optional.of(externalAPIResponse).map(this::checkAvailability).orElseThrow();
        AvailableSpacesResponse availableSpacesResponse = new AvailableSpacesResponse();
        availableSpacesResponse.setAvailable(availability);

        return Mono.just(availableSpacesResponse);
    }

    private boolean checkAvailability(ExternalAPIResponse externalAPIResponse) {
        return externalAPIResponse.getAvailableSpace() != 0;
    }

    @Override
    public Mono<BookingsRef> createBookings(@NonNull BookingsSpec bookingsSpec) {
        primaryKeyIncrement += 1;
        bookingsSpec.setBookingRef(String.valueOf(primaryKeyIncrement));
        Mono<BookingsSpec> bookingsSpecMono = bookingsRepository.save(bookingsSpec);

        return bookingsSpecMono.map(bookingsSpecValue -> new BookingsRef(bookingsSpecValue.getBookingRef()));
    }
}
