package com.maersk.containerbookingservice.service;

import com.maersk.containerbookingservice.model.AvailableSpacesResponse;
import com.maersk.containerbookingservice.model.BookingsRef;
import com.maersk.containerbookingservice.model.BookingsSpec;
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
