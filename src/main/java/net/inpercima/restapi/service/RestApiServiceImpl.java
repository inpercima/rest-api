package net.inpercima.restapi.service;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Strings;

/**
 * @author Marcel Jänicke
 * @since 08.01.2017
 */
@Component("restApiService")
public class RestApiServiceImpl implements RestApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiServiceImpl.class);

    @Value("${app.clientId}")
    private String clientId;

    @Value("${app.clientSecret}")
    private String clientSecret;

    @Value("${app.redirectUri}")
    private String redirectUri;

    private final RestTemplate restTemplate;

    @Override
    public String getClientId() {
        return clientId;
    }

    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(final String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(final String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public RestApiServiceImpl() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    @Override
    public <T> T postForObject(final String url, final String code, final Class<T> clazz) {
        return restTemplate.postForObject(url, createTokenParams(code), clazz);
    }

    @Override
    public void postForObject(final String url, final String accessToken) {
        restTemplate.postForLocation(url, createTokenParams(accessToken));
    }

    @Override
    public <T> HttpEntity<T> getForObject(final String url, final String applicationType, final String accessToken,
            final Class<T> clazz) {
        LOGGER.debug("get '{}' with '{}' for token '{}'", url, applicationType, accessToken);
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
