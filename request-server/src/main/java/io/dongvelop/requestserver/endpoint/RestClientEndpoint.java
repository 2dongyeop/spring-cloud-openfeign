package io.dongvelop.requestserver.endpoint;

import io.dongvelop.requestserver.payload.request.CommonRequest;
import io.dongvelop.requestserver.payload.response.CommonResponse;
import io.dongvelop.requestserver.service.RestClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 16
 * @description RestClient 호출 Endpoint
 */
@Slf4j
@RestController
@RequestMapping("/request/rest-client")
@RequiredArgsConstructor
public class RestClientEndpoint {

    private final RestClientService restClientService;

    /**
     * RestClient로 외부 API를 호출하는 예시
     *
     * @param request : 공통 요청 형태
     * @return : 공통 응답 형태
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse webClient(@RequestBody final CommonRequest request) {
        log.info("request[{}]", request);
        return restClientService.send(request);
    }

    /**
     * RestClient Retry를 보여주는 예시
     *
     * @param request : 공통 요청 형태
     * @return : 공통 응답 형태
     */
    @PostMapping(value = "/retry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse webClientRetry(@RequestBody final CommonRequest request) {
        log.info("request[{}]", request);
        return restClientService.sendRetry(request);
    }

    /**
     * RestClient - 에러 코드별로 분리 처리 예시
     *
     * @param request : 공통 요청 형태
     * @return : 공통 응답 형태
     */
    @PostMapping(value = "/error", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse get500error(@RequestBody final CommonRequest request) {
        log.info("request[{}]", request);
        return restClientService.get500status(request);
    }
}
