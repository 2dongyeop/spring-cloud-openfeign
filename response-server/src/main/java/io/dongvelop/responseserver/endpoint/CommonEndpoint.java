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

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 11
 * @description 공통 응답 Endpoint
 */
@Slf4j
@RestController
@RequestMapping("/response")
public class CommonEndpoint {

    /**
     * 공통 요청 Endpoint <br/>
     * 응답 시간에 서버 시간(now)을 더하여 응답
     *
     * @param request : 공통 요청 형태
     * @return : 공통 응답 형태
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse commonEndpoint(@RequestBody final CommonRequest request) {
        log.info("request[{}]", request);

        return CommonResponse.of(request);
    }

    /**
     * Retry 실행을 보여주기 위한 Endpoint <br/>
     *
     * @param request : 공통 요청 형태
     * @return : 공통 응답 형태
     * @throws IOException
     */
    @PostMapping(value = "/retry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse commonRetryEndpoint(@RequestBody final CommonRequest request) throws IOException {
        log.info("request[{}]", request);

        log.error("start retry test");
        throw new IOException("retry test");
    }
}
