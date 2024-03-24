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
}
