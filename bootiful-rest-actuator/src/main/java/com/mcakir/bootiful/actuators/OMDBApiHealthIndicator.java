package com.mcakir.bootiful.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class OmdbApiHealthIndicator implements HealthIndicator {

    final String API_CHECK_URL = "http://www.omdbapi.com/?t=matrix&y=1999&apikey=PlsBanMe";

    @Override
    public Health health() {

        try {

            URI uri = new URI(API_CHECK_URL);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class);

            return Health.up().build();

        } catch (URISyntaxException e) {

        } catch (HttpClientErrorException e) {

            HttpStatus status = e.getStatusCode();

            String detail = status.equals(HttpStatus.UNAUTHORIZED) ? "Api key is invalid" : e.getLocalizedMessage();

            return Health.down()
                    .withDetail("code", status.value())
                    .withDetail("detail", detail).build();

        }

        return Health.down().withDetail("detail", "Couldn't reach the api").build();
    }
}
