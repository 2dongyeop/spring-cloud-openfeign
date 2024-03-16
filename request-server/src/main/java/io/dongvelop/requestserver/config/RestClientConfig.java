package io.dongvelop.requestserver.config;

import io.dongvelop.requestserver.common.CommonConst;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 16
 * @description RestClient 설정 클래스
 */
@Configuration
public class RestClientConfig {
    @Value("${url.server.response.endpoint}")
    private String responseServerUrl;
    @Value("${restTemplate.connectTimeOut}")
    private Long connectTimeOut;
    @Value("${restTemplate.readTimeOut}")
    private Long readTimeOut;

    @Bean
    public RestClient restClient() {
        // 아래와 같이 Timeout 설정하는 부분은 RestTemplate 와 동일
        final ClientHttpRequestFactory requestFactory = ClientHttpRequestFactories.get(ClientHttpRequestFactorySettings.DEFAULTS
                .withReadTimeout(Duration.ofSeconds(readTimeOut))
                .withConnectTimeout(Duration.ofSeconds(connectTimeOut)));

        // 기본적인 공통 헤더/Url 설정 방식은 WebClient 와 동일
        return RestClient.builder()
                .baseUrl(responseServerUrl)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(CommonConst.AUTHORIZATION, CommonConst.BEARER);
                    httpHeaders.add(CommonConst.CONTENT_TYPE_KEY, MediaType.APPLICATION_JSON_VALUE);
                })
                .requestFactory(requestFactory)
                .build();
    }
}
