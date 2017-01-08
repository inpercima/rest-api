package net.inpercima.rest_api.service;

import org.springframework.http.HttpEntity;

/**
 * @author Marcel Jänicke
 * @since 10.02.2015
 */
public interface RestApiService {

    String getClientId();

    String getRedirectUri();

    <T> T postForObject(String url, String code, Class<T> clazz);

    void postForObject(String url, String accessToken);

    <T> HttpEntity<T> getForObject(String url, String applicationType, String accessToken, Class<T> clazz);

}
