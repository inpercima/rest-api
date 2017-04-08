package net.inpercima.restapi.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class RestApiServiceImplTest {

    private final RestApiService restApiService = new RestApiServiceImpl();

    @Test
    public void getForObject() {
        final String url = "https://httpbin.org/get";
        final Object object = restApiService.getForObject(url, "application/json", "", Object.class).getBody();
        assertNotNull(object);
    }

}
