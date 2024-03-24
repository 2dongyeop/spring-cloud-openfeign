package io.dongvelop.requestserver.service;

import io.dongvelop.requestserver.payload.request.CommonRequest;
import io.dongvelop.requestserver.payload.response.CommonResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 16
 * @description Spring 6에 추가된 HttpInterface
 */
public interface HttpInterfaceClient {

    @PostExchange(contentType = MediaType.APPLICATION_JSON_VALUE, accept = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse send(@RequestBody CommonRequest request);

    @PostExchange(value = "/retry", contentType = MediaType.APPLICATION_JSON_VALUE, accept = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse sendRetry(@RequestBody CommonRequest request);

    @PostExchange(value = "/retry", contentType = MediaType.APPLICATION_JSON_VALUE, accept = MediaType.APPLICATION_JSON_VALUE)
    CommonResponse get500Error(@RequestBody CommonRequest request);
}
