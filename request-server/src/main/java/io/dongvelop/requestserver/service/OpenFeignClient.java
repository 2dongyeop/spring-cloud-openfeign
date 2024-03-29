package io.dongvelop.requestserver.service;

import io.dongvelop.requestserver.config.FeignClientRetryConfig;
import io.dongvelop.requestserver.payload.request.CommonRequest;
import io.dongvelop.requestserver.payload.response.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 24
 * @description Spring Cloud OpenFeign 예제 코드
 */
@FeignClient(value = "openFeignClient", url = "${url.server.response.endpoint}", configuration = FeignClientRetryConfig.class)
public interface OpenFeignClient {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse send(@RequestBody CommonRequest request);

    @PostMapping(path = "${url.server.response.timeout}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse sendTimeout(@RequestBody CommonRequest request);

    @PostMapping(path = "${url.server.response.retry}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse sendRetry(@RequestBody CommonRequest request);

    @PostMapping(path = "${url.server.response.bad-request}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse sendBadRequest(@RequestBody CommonRequest request);
}
