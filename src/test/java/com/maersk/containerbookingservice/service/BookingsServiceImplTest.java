package com.maersk.containerbookingservice.service;

import com.maersk.containerbookingservice.TestUtil;
import com.maersk.containerbookingservice.configuration.ExternalServiceClient;
import com.maersk.containerbookingservice.model.AvailableSpacesResponse;
import com.maersk.containerbookingservice.model.BookingsRef;
import com.maersk.containerbookingservice.model.BookingsSpec;
import com.maersk.containerbookingservice.model.ExternalAPIResponse;
import com.maersk.containerbookingservice.repository.BookingsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingsServiceImplTest {

    @Mock
    private ExternalServiceClient externalServiceClient;
    @Mock
    private BookingsRepository bookingsRepository;

    @InjectMocks
    BookingsServiceImpl bookingsService;

    @Test
    void givenBookingSpecification_whenSpacesAvailable_thenShouldReturnTrue() {
        BookingsSpec bookingsSpec = TestUtil.buildBookingSpec("957000002", Instant.now().toString());
        ExternalAPIResponse externalAPIResponse = TestUtil.buildExternalAPIResponse(10);
        when(externalServiceClient.callExternalAPI("/checkAvailable")).thenReturn(externalAPIResponse);
        Mono<AvailableSpacesResponse> availableSpacesMono = bookingsService.checkAvailableSpaces(bookingsSpec);

        StepVerifier.create(availableSpacesMono).
                assertNext(actual -> assertThat(actual.isAvailable()).isTrue()).verifyComplete();
    }

    @Test
    void givenBookingSpecification_whenSpacesAreNotAvailable_thenShouldReturnFalse() {
        BookingsSpec bookingsSpec = TestUtil.buildBookingSpec("957000002", Instant.now().toString());
        ExternalAPIResponse externalAPIResponse = TestUtil.buildExternalAPIResponse(0);
        when(externalServiceClient.callExternalAPI("/checkAvailable")).thenReturn(externalAPIResponse);
        Mono<AvailableSpacesResponse> availableSpacesMono = bookingsService.checkAvailableSpaces(bookingsSpec);

        StepVerifier.create(availableSpacesMono).
                assertNext(actual -> assertThat(actual.isAvailable()).isFalse()).verifyComplete();
    }


    @Test
    void givenBookingSpec_whenSaveNewBooking_thenShouldReturnRef() throws Exception {
        int bookingRefIncrement = 957000001;
        BookingsSpec bookingsSpec = TestUtil.buildBookingSpec("957000002", Instant.now().toString());
        when(bookingsRepository.save(bookingsSpec)).thenReturn(Mono.just(bookingsSpec));
        Mono<BookingsRef> bookingRef = bookingsService.createBookings(bookingsSpec);

        StepVerifier.create(bookingRef).assertNext(actual -> assertThat(Integer.parseInt(actual.getBookingRef()))
                        .isEqualTo(bookingRefIncrement))
                .verifyComplete();
    }
}