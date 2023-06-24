package com.shperev.containerbookingservice.service;

import com.shperev.containerbookingservice.model.AvailableSpacesResponse;
import com.shperev.containerbookingservice.model.BookingsRef;
import com.shperev.containerbookingservice.model.BookingsSpec;
import reactor.core.publisher.Mono;


public interface BookingsService {
    /**
     * The method will check available spaces
     *
     * @param bookingsSpec {@link  BookingsSpec}
     * @return {@link AvailableSpacesResponse}
     */
    Mono<AvailableSpacesResponse> checkAvailableSpaces(BookingsSpec bookingsSpec);

    /**
     * Create container booking with the provided values
     *
     * @param bookingsSpec {@link  BookingsSpec}
     * @return {@link  BookingsRef}
     */
    Mono<BookingsRef> createBookings(BookingsSpec bookingsSpec);
}
