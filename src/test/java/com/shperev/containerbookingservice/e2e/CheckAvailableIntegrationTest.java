package com.shperev.containerbookingservice.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shperev.containerbookingservice.ContainerBookingServiceApplication;
import com.shperev.containerbookingservice.TestUtil;
import com.shperev.containerbookingservice.model.AvailableSpacesResponse;
import com.shperev.containerbookingservice.model.BookingsSpec;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = ContainerBookingServiceApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckAvailableIntegrationTest {

  @LocalServerPort private int port;

  @Test
  void givenBookingSpec_whenExternalApiResponse_thenShouldResponseBackAvailability()
      throws Exception {
    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    BookingsSpec bookingsSpec = TestUtil.buildBookingSpec(null, null);

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonString = objectMapper.writeValueAsString(bookingsSpec);

    HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);

    ResponseEntity<String> response =
        restTemplate.exchange(
            createURLWithPort("/api/bookings/checkAvailable"),
            HttpMethod.POST,
            entity,
            String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();

    AvailableSpacesResponse availableSpacesResponse =
        objectMapper.readValue(response.getBody(), AvailableSpacesResponse.class);

    assertThat(availableSpacesResponse.getAvailability()).isTrue();
  }

  public String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }
}
