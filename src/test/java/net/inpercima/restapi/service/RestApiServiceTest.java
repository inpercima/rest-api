package net.inpercima.restapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RestApiServiceTest {

    private final RestApiService restApiService = new RestApiService();

    @Test
    public void getForObject() {
        final String url = "https://httpbin.org/get";
        final Object object = restApiService.getForObject(url, "application/json", "", Object.class).getBody();
        Assertions.assertNotNull(object);
    }
}
