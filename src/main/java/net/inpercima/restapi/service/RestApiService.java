package net.inpercima.restapi.service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import com.google.common.base.Strings;

/**
 * @author Marcel Jänicke
 * @since 08.01.2017
 */
@Slf4j
@Getter
@Setter
@Service
public class RestApiService {

    @Value("${app.clientId}")
    private String clientId;

    @Value("${app.clientSecret}")
    private String clientSecret;

    @Value("${app.redirectUri}")
    private String redirectUri;

    private final RestTemplate restTemplate;

    public RestApiService() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    public <T> T postForObject(final String url, final String code, final Class<T> clazz) {
        return restTemplate.postForObject(url, createTokenParams(code), clazz);
    }

    public void postForObject(final String url, final String accessToken) {
        restTemplate.postForLocation(url, createTokenParams(accessToken));
    }

    public <T> HttpEntity<T> getForObject(final String url, final String applicationType, final String accessToken,
            final Class<T> clazz) {
        log.debug("get '{}' with '{}' for token '{}'", url, applicationType, accessToken);
        return restTemplate.exchange(url, HttpMethod.GET, createHttpEntity(accessToken, applicationType), clazz,
                createAccessParams(accessToken));
    }

    private MultiValueMap<String, String> createTokenParams(final String code) {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(RestApiConstants.CLIENT_ID, clientId);
        params.add(RestApiConstants.CLIENT_SECRET, clientSecret);
        params.add(RestApiConstants.GRANT_TYPE, RestApiConstants.AUTHORIZATION_CODE);
        params.add(RestApiConstants.REDIRECT_URI, redirectUri);
        params.add(RestApiConstants.CODE, code);
        return params;
    }

    private static MultiValueMap<String, String> createAccessParams(final String accessToken) {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(RestApiConstants.ACCESS_TOKEN, accessToken);
        return params;
    }

    private static <T> HttpEntity<T> createHttpEntity(final String accessToken, final String applicationType) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set(RestApiConstants.ACCEPT, applicationType);
        headers.set(RestApiConstants.AUTHORIZATION, RestApiConstants.BEARER.concat(Strings.nullToEmpty(accessToken)));
        headers.set(RestApiConstants.ACCEPT_CHARSET, StandardCharsets.UTF_8.displayName());
        final HttpEntity<T> entity = new HttpEntity<>(headers);
        return entity;
    }
}
