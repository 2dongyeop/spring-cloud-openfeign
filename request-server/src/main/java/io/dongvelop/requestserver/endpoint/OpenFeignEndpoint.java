package io.dongvelop.requestserver.endpoint;

import io.dongvelop.requestserver.payload.request.CommonRequest;
import io.dongvelop.requestserver.payload.response.CommonResponse;
import io.dongvelop.requestserver.service.OpenFeignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 24
 * @description OpenFeign 호출 Endpoint
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/request/open-feign")
public class OpenFeignEndpoint {

    private final OpenFeignService openFeignService;

    /**
     * OpenFeign으로 외부 API를 호출하는 예시
     *
     * @param request : 공통 요청 형태
     * @return : 공통 응답 형태
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse send(@RequestBody final CommonRequest request) {
        log.info("request[{}]", request);
        return openFeignService.send(request);
    }
}