package io.dongvelop.responseserver.endpoint;

import io.dongvelop.responseserver.payload.request.CommonRequest;
import io.dongvelop.responseserver.payload.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 11
 * @description 공통 응답 Endpoint
 */
@Slf4j
@RestController
@RequestMapping("/response/rest-template")
public class RestTemplateEndpoint {

    /**
     * 공통 요청 Endpoint <br/>
     * 응답 시간에 서버 시간(now)을 더하여 응답
     *
     * @param request : 공통 요청 형태
     * @return : 공통 응답 형태
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse restTemplate(@RequestBody final CommonRequest request) {
        log.info("request[{}]", request);

        return new CommonResponse(
                request.writer(),
                request.message(),
                LocalDateTime.now()
        );
    }

    @PostMapping(value = "/retry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse restTemplateRetry(@RequestBody final CommonRequest request) throws IOException {
        log.info("request[{}]", request);

        log.error("restTemplate retry test");
        throw new IOException("restTemplate retry test");
    }
}
