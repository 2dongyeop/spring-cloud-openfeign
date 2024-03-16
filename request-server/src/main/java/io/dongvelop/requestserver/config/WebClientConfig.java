package io.dongvelop.requestserver.config;

import io.dongvelop.requestserver.common.CommonConst;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 16
 * @description WebClient 설정 클래스
 */
@Configuration
public class WebClientConfig {
    @Value("${url.server.response.endpoint}")
    private String responseServerUrl;
    @Value("${webClient.timeout.connect}")
    private Integer connectTimeout;
    @Value("${webClient.timeout.response}")
    private Long responseTimeout;
    @Value("${webClient.timeout.read}")
    private Long readTimeout;
    @Value("${webClient.timeout.write}")
    private Long writeTimeout;
    @Value("${webClient.maxMemorySize}")
    private int maxMemorySize;

    /**
     * 공통으로 쓰이는 WebClient
     */
    @Bean
    public WebClient webClient() {

        // 타임아웃 설정을 위한 HttpClient
        final HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout) // 연결 타임아웃
                .responseTimeout(Duration.ofSeconds(responseTimeout))     // 응답 타임아웃
                .doOnConnected(conn -> conn
                        // 읽기 작업 타임아웃
                        .addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.SECONDS))
                        // 쓰기 작업 타임아웃
                        .addHandlerLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.SECONDS))
                );

        /*
         * Spring WebFlux 에서는 애플리케이션 메모리 문제를 피하기 위해 in-memory buffer 값이 256KB로 기본설정 되어 있음.
         * 따라서 256KB 보다 큰 HTTP 메시지를 처리 시도 → DataBufferLimitException 에러 발생
         * in-memory buffer 값을 늘려주기 위한 설정 추가
         */
        return WebClient.builder()
                .baseUrl(responseServerUrl)
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(maxMemorySize))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(CommonConst.AUTHORIZATION, CommonConst.BEARER);
                    httpHeaders.add(CommonConst.CONTENT_TYPE_KEY, MediaType.APPLICATION_JSON_VALUE);
                })
                .build();
    }
}
