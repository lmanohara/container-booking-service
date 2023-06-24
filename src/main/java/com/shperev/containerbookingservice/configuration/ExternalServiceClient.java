package com.shperev.containerbookingservice.configuration;

import com.shperev.containerbookingservice.model.ExternalAPIResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * The client for calling the external REST api
 */
@Service
public class ExternalServiceClient {
    @Value("${app.external-api-url}")
    private String externalAPIUrl;


    /**
     * This method is create URI for given context path
     *
     * @param contextPath context path
     * @return {@link  ExternalAPIResponse}
     */
    public Mono<ExternalAPIResponse> callExternalAPI(String contextPath) {
        WebClient webClient = WebClient.builder().baseUrl(externalAPIUrl).build();

        return webClient.get().uri(contextPath).retrieve().bodyToMono(ExternalAPIResponse.class);
    }
}
