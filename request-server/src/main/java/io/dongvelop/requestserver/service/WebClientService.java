package io.dongvelop.requestserver.service;

import io.dongvelop.requestserver.payload.request.CommonRequest;
import io.dongvelop.requestserver.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 13
 * @description WebClient 사용 예제 Service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WebClientService {
    @Value("${url.server.response.retry}")
    private String retryRequestPath;
    @Value("${webClient.retry.maxAttempt}")
    private Long retryMaxAttempt;
    @Value("${webClient.retry.delay}")
    private Long retryDelay;

    private final WebClient webClient;

    /**
     * WebClient로 외부 API를 호출하는 예시
     */
    public CommonResponse send(final CommonRequest request) {
        log.info("request[{}]", request);

        return webClient.post()
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), CommonRequest.class)
                .retrieve()
                .bodyToMono(CommonResponse.class)
                .block();
    }


    /**
     * WebClient를 이용한 재시도 처리 <br/>
     *
     * @see <a href=https://www.baeldung.com/spring-webflux-retry>WebClient Retry</a>
     */
    public CommonResponse sendRetry(final CommonRequest request) {
        log.info("request[{}]", request);

        return webClient.post()
                .uri(retryRequestPath)
                .body(Mono.just(request), CommonRequest.class)
                .retrieve()
                .bodyToMono(CommonResponse.class)
                .retryWhen(Retry.fixedDelay(retryMaxAttempt, Duration.ofSeconds(retryDelay))) // 1초로 고정된 간격으로 재시도
//                .retryWhen(Retry.max(3))                    // 매우 짧은 간격으로 재시도 - 복구할 기회를 주지 않아 위험함.
//                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))                // 2초. 4초. 8초의 백오프 전략으로 제시도
                .block();
    }

    /**
     * Response Server로부터 받은 HTTP 상태코드별로 에러 처리
     */
    public CommonResponse get500status(final CommonRequest request) {
        log.info("request[{}]", request);

        return webClient.post()
                .uri(retryRequestPath)
                .body(Mono.just(request), CommonRequest.class)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new WebClientResponseException(
                                response.statusCode().value(),
                                String.format("5xx error found. %s", response.bodyToMono(String.class)),
                                response.headers().asHttpHeaders(), null, null
                        )
                ))
                .bodyToMono(CommonResponse.class)
                .block();
    }
}