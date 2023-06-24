package com.shperev.containerbookingservice.e2e;

import com.shperev.containerbookingservice.ContainerBookingServiceApplication;
import com.shperev.containerbookingservice.TestUtil;
import com.shperev.containerbookingservice.repository.BookingsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;

@SpringBootTest(classes = ContainerBookingServiceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingRepositoryIntegrationTest {

    @Autowired
    private BookingsRepository bookingsRepository;

    @Test
    void givenRecordAreInserted_whenDbIsQueried_thenShouldReturnRecord() {
        Mono<Long> saveAndCount = bookingsRepository.deleteAll()
                .thenMany(bookingsRepository.saveAll(Mono.just(TestUtil.buildBookingSpec("957000002", Instant.now().toString())))).last()
                .flatMap(records -> bookingsRepository.count());

        StepVerifier.create(saveAndCount).expectNext(1L).verifyComplete();

    }

}
