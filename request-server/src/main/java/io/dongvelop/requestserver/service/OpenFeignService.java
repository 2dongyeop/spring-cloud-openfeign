package io.dongvelop.requestserver.service;

import io.dongvelop.requestserver.payload.request.CommonRequest;
import io.dongvelop.requestserver.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 24
 * @description OpenFeign 사용 예제 Service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OpenFeignService {

    private final OpenFeignClient feignClient;

    /**
     * FeignClient로 외부 API를 호출하는 예시
     */
    public CommonResponse send(final CommonRequest request) {
        log.info("request[{}]", request);

        return feignClient.send(request);
    }

    /**
     * FeignClient Timeout 설정 확인 예시
     * Timeout을 1초로 제한했을 때, 응답 서버에서는 3초 뒤에 응답을 주는 상황
     */
    public CommonResponse sendTimeout(final CommonRequest request) {
        log.info("request[{}]", request);

        return feignClient.sendTimeout(request);
    }

    /**
     * FeignClient 재시도 예시
     */
    public CommonResponse sendRetry(final CommonRequest request) {
        log.info("request[{}]", request);

        return feignClient.sendRetry(request);
    }

    /**
     * FeignClient Error Decoder 예시
     */
    public CommonResponse sendBadRequest(final CommonRequest request) {
        log.info("request[{}]", request);

        return feignClient.sendBadRequest(request);
    }
}
