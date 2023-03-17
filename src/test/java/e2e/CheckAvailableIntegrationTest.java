package e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maersk.containerbookingservice.ContainerBookingServiceApplication;
import com.maersk.containerbookingservice.TestUtil;
import com.maersk.containerbookingservice.model.AvailableSpacesResponse;
import com.maersk.containerbookingservice.model.BookingsSpec;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContainerBookingServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckAvailableIntegrationTest {

    @LocalServerPort
    private int port;


    @Test
    void givenBookingSpec_whenExternalApiResponse_thenShouldResponseBackAvailability() throws Exception {
        TestRestTemplate restTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        BookingsSpec bookingsSpec = TestUtil.buildBookingSpec(null, null);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(bookingsSpec);

        HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/bookings/checkAvailable"),
                HttpMethod.POST, entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        AvailableSpacesResponse availableSpacesResponse = objectMapper.readValue(response.getBody(), AvailableSpacesResponse.class);

        assertThat(availableSpacesResponse.isAvailable()).isTrue();
    }


    public String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
