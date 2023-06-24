package com.shperev.containerbookingservice.configuration;


import com.shperev.containerbookingservice.TestUtil;
import com.shperev.containerbookingservice.model.ExternalAPIResponse;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExternalServiceClientTest {

    private final ExternalServiceClient externalServiceClient = new ExternalServiceClient();
    String externalAPIUrl = "http://localhost/api";

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