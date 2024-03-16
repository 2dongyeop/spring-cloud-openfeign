package io.dongvelop.requestserver.service;

import io.dongvelop.requestserver.payload.request.CommonRequest;
import io.dongvelop.requestserver.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 16
 * @description RestClient 사용 예제 Service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RestClientService {

    private final RestClient restClient;

    @Value("${url.server.response.retry}")
    private String retryRequestPath;

    /**
     * RestClient로 외부 API를 호출하는 예시
     */
    public CommonResponse send(final CommonRequest request) {
        log.info("request[{}]", request);

        return restClient.post()
                .accept(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(CommonResponse.class);
    }

    /**
     * RestClient를 이용한 재시도 처리 <br/>
     * RestTemplate과 마찬가지로 Retry를 지원하지 않아, Spring-Retry를 이용해야 함. <br/>
     * 재시도 전략은 백오프 전략
     */
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000L))
    public CommonResponse sendRetry(final CommonRequest request) {
        log.info("request[{}]", request);

        return restClient.post()
                .uri(retryRequestPath)
                .body(request)
                .retrieve()
                .body(CommonResponse.class);
    }

    /**
     * Response Server로부터 받은 HTTP 상태코드별로 에러 처리
     */
    public CommonResponse get500status(final CommonRequest request) {
        log.info("request[{}]", request);

        return restClient.post()
                .uri(retryRequestPath)
                .body(request)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (httpRequest, httpResponse) -> {
                    log.error("errorStatus[{}], errorText[{}]", httpResponse.getStatusCode(), httpResponse.getStatusText());
                    throw new RuntimeException();
                })
                .onStatus(HttpStatusCode::is5xxServerError, (httpRequest, httpResponse) -> {
                    log.error("errorStatus[{}], errorText[{}]", httpResponse.getStatusCode(), httpResponse.getStatusText());
                    throw new RuntimeException();
                })
                .body(CommonResponse.class);
    }
}
