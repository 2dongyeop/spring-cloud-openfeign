package io.dongvelop.requestserver.endpoint;

import io.dongvelop.requestserver.payload.request.CommonRequest;
import io.dongvelop.requestserver.payload.response.CommonResponse;
import io.dongvelop.requestserver.service.HttpInterfaceService;
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
 * @description HttpInterface 호출 Endpoint
 */
@Slf4j
@RestController
@RequestMapping("/request/http-interface")
@RequiredArgsConstructor
public class HttpInterfaceEndpoint {

    private final HttpInterfaceService httpInterfaceService;

    /**
     * HttpInterface로 외부 API를 호출하는 예시
     *
     * @param request : 공통 요청 형태
     * @return : 공통 응답 형태
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse send(@RequestBody final CommonRequest request) {
        log.info("request[{}]", request);
        return httpInterfaceService.send(request);
    }

    /**
     * HttpInterface Retry를 보여주는 예시
     *
     * @param request : 공통 요청 형태
     * @return : 공통 응답 형태
     */
    @PostMapping(value = "/retry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse retry(@RequestBody final CommonRequest request) {
        log.info("request[{}]", request);
        return httpInterfaceService.sendRetry(request);
    }

    /**
     * HttpInterface - 에러 코드별로 분리 처리 예시
     *
     * @param request : 공통 요청 형태
     * @return : 공통 응답 형태
     */
    @PostMapping(value = "/error", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse get500error(@RequestBody final CommonRequest request) {
        log.info("request[{}]", request);
        return httpInterfaceService.get500status(request);
    }
}
