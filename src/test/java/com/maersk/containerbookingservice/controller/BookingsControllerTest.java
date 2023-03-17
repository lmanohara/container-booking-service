package com.maersk.containerbookingservice.controller;

import com.maersk.containerbookingservice.TestUtil;
import com.maersk.containerbookingservice.model.AvailableSpacesResponse;
import com.maersk.containerbookingservice.model.BookingsRef;
import com.maersk.containerbookingservice.model.BookingsSpec;
import com.maersk.containerbookingservice.service.BookingsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingsControllerTest {

    @Mock
    private BookingsService bookingsService;

    @InjectMocks
    BookingsController bookingsController;

    @Test
    void givenBookingSpec_whenExternalAPIResponse_thenShouldReturnMono() {
        BookingsSpec bookingsSpec = TestUtil.buildBookingSpec("957000002", Instant.now().toString());
        AvailableSpacesResponse availableSpacesResponse = TestUtil.buildAvailableSpaces(true);
        Mono<AvailableSpacesResponse> availableSpacesResponseMono = Mono.just(availableSpacesResponse);
        when(bookingsService.checkAvailableSpaces(bookingsSpec)).thenReturn(availableSpacesResponseMono);

        Mono<AvailableSpacesResponse> actualMono = bookingsController.checkAvailableSpaces(bookingsSpec);

        assertThat(actualMono.block()).isNotNull();
    }

    @Test
    void givenBookingSpec_whenSaveToDatabase_thenShouldReturnMono() throws Exception {
        BookingsSpec bookingsSpec = TestUtil.buildBookingSpec("957000002", Instant.now().toString());
        when(bookingsService.createBookings(bookingsSpec)).thenReturn(Mono.just(new BookingsRef("957000001")));

        Mono<BookingsRef> bookings = bookingsController.createBookings(bookingsSpec);

        assertThat(bookings.block()).isNotNull();
    }
}