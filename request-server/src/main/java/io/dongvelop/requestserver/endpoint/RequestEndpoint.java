package io.dongvelop.requestserver.endpoint;

import io.dongvelop.requestserver.payload.request.CommonRequest;
import io.dongvelop.requestserver.payload.response.CommonResponse;
import io.dongvelop.requestserver.service.resttemplate.RestTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 11
 * @description 공통 요청 Endpoint
 */
@Slf4j
@RestController
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestEndpoint {

    private final RestTemplateService restTemplateService;

    /**
     * RestTemplate 으로 외부 API를 호출하는 예시
     *
     * @param request : 공통 요청 형태
     * @return : 공통 응답 형태
     */
    @PostMapping(value = "/rest-template", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse restTemplate(@RequestBody final CommonRequest request) {
        log.info("request[{}]", request);
        return restTemplateService.send(request);
    }

    @PostMapping(value = "/rest-template/retry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse restTemplateRetry(@RequestBody final CommonRequest request) {
        log.info("request[{}]", request);
        return restTemplateService.sendRetry(request);
    }
}
