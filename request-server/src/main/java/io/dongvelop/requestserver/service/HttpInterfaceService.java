package io.dongvelop.requestserver.service;

import io.dongvelop.requestserver.common.CustomException;
import io.dongvelop.requestserver.payload.request.CommonRequest;
import io.dongvelop.requestserver.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 16
 * @description HttpInterface 사용 예제 Service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HttpInterfaceService {

    private final RestClient restClient;

    /**
     * HttpInterface로 외부 API를 호출하는 예시 <br/>
     * 1. RestClient 를 이용해 객체를 생성할 경우
     */
    public CommonResponse send(final CommonRequest request) {
        log.info("request[{}]", request);

        return HttpInterfaceBuilder
                .builder(restClient, HttpInterfaceClient.class)
                .send(request);
    }

    /**
     * HttpInterface를 이용한 재시도 처리 <br/>
     * Retry를 지원하지 않아, Spring-Retry를 이용해야 함. <br/>
     */
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000L))
    public CommonResponse sendRetry(final CommonRequest request) {
        log.info("request[{}]", request);

        return HttpInterfaceBuilder
                .builder(restClient, HttpInterfaceClient.class)
                .sendRetry(request);
    }

    /**
     * Response Server로부터 받은 HTTP 상태코드별로 에러 처리
     */
    public CommonResponse get500status(final CommonRequest request) {
        log.info("request[{}]", request);

        final RestClient reBuildRestClient = restClient.mutate()
                .defaultStatusHandler(HttpStatusCode::isError, (httpRequest, httpResponse) -> {
                    log.error("errorStatus[{}], errorText[{}]", httpResponse.getStatusCode(), httpResponse.getStatusText());
                    throw new CustomException("http interface error catch");
                })
                .build();

        return HttpInterfaceBuilder
                .builder(reBuildRestClient, HttpInterfaceClient.class)
                .get500Error(request);
    }
}