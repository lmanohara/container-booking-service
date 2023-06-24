package com.shperev.containerbookingservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.shperev.containerbookingservice.TestUtil;
import com.shperev.containerbookingservice.model.AvailableSpacesResponse;
import com.shperev.containerbookingservice.model.BookingsRef;
import com.shperev.containerbookingservice.model.BookingsSpec;
import com.shperev.containerbookingservice.service.BookingsService;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class BookingsControllerTest {

  @InjectMocks BookingsController bookingsController;
  @Mock private BookingsService bookingsService;

  @Test
  void givenBookingSpec_whenExternalAPIResponse_thenShouldReturnMono() {
    BookingsSpec bookingsSpec = TestUtil.buildBookingSpec("957000002", Instant.now().toString());
    AvailableSpacesResponse availableSpacesResponse = TestUtil.buildAvailableSpaces(true);
    Mono<AvailableSpacesResponse> availableSpacesResponseMono = Mono.just(availableSpacesResponse);
    when(bookingsService.checkAvailableSpaces(bookingsSpec))
        .thenReturn(availableSpacesResponseMono);

    Mono<AvailableSpacesResponse> actualMono =
        bookingsController.checkAvailableSpaces(bookingsSpec);

    assertThat(actualMono.block()).isNotNull();
  }

  @Test
  void givenBookingSpec_whenSaveToDatabase_thenShouldReturnMono() throws Exception {
    BookingsSpec bookingsSpec = TestUtil.buildBookingSpec("957000002", Instant.now().toString());
    when(bookingsService.createBookings(bookingsSpec))
        .thenReturn(Mono.just(new BookingsRef("957000001")));

    Mono<BookingsRef> bookings = bookingsController.createBookings(bookingsSpec);

    assertThat(bookings.block()).isNotNull();
  }
}
