package com.shperev.containerbookingservice.service;

import com.shperev.containerbookingservice.configuration.ExternalServiceClient;
import com.shperev.containerbookingservice.model.AvailableSpacesResponse;
import com.shperev.containerbookingservice.model.BookingsRef;
import com.shperev.containerbookingservice.model.BookingsSpec;
import com.shperev.containerbookingservice.model.ExternalAPIResponse;
import com.shperev.containerbookingservice.repository.BookingsRepository;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Function;

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
        Mono<ExternalAPIResponse> externalAPIResponseMono = externalServiceClient.callExternalAPI("/checkAvailable");
        return externalAPIResponseMono.map(this::mapToAvailableSpanceResponse);
    }

    private AvailableSpacesResponse mapToAvailableSpanceResponse(ExternalAPIResponse externalAPIResponse) {
        AvailableSpacesResponse availableSpacesResponse = new AvailableSpacesResponse();
        boolean isSpaceAvailable = externalAPIResponse.getAvailableSpace() > 0;
        availableSpacesResponse.setAvailable(isSpaceAvailable);

        return availableSpacesResponse;
    }

    @Override
    public Mono<BookingsRef> createBookings(@NonNull BookingsSpec bookingsSpec) {
        primaryKeyIncrement += 1;
        bookingsSpec.setBookingRef(String.valueOf(primaryKeyIncrement));
        Mono<BookingsSpec> bookingsSpecMono = bookingsRepository.save(bookingsSpec);

        return bookingsSpecMono.map(bookingsSpecValue -> new BookingsRef(bookingsSpecValue.getBookingRef()));
    }
}
