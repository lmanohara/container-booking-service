package com.maersk.containerbookingservice.configuration;


import com.maersk.containerbookingservice.TestUtil;
import com.maersk.containerbookingservice.model.ExternalAPIResponse;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExternalServiceClientTest {

    String externalAPIUrl = "http://localhost/api";
    private final ExternalServiceClient externalServiceClient = new ExternalServiceClient();

    void givenContextPath_whenCallExternalAPI_thenShouldReturnAvailableSpaces() {

        ReflectionTestUtils.setField(externalServiceClient, "externalAPIUrl", externalAPIUrl);

        String contextPath = "/checkAvailable";
        externalServiceClient.callExternalAPI(contextPath);
        RestTemplate restTemplate = mock(RestTemplate.class);
        ExternalAPIResponse externalAPIResponse = TestUtil.buildExternalAPIResponse(2);

        when(restTemplate.getForObject(String.join(externalAPIUrl, contextPath), ExternalAPIResponse.class))
                .thenReturn(externalAPIResponse);

    }

}