package com.maersk.containerbookingservice.configuration;

import com.maersk.containerbookingservice.model.ExternalAPIResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * The client for calling the external REST api
 */
@Component
public class ExternalServiceClient {
    @Value("${app.external-api-url}")
    private String externalAPIUrl;


    /**
     * This method is create URI for given context path
     *
     * @param contextPath context path
     *
     * @return {@link  ExternalAPIResponse}
     */
    public ExternalAPIResponse callExternalAPI(String contextPath) {
        RestTemplate restTemplate = new RestTemplate();
        String apiURL = externalAPIUrl + contextPath;

        return restTemplate.getForObject(apiURL, ExternalAPIResponse.class);
    }
}
