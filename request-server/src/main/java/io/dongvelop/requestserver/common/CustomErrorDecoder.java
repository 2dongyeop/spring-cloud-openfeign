package io.dongvelop.requestserver.common;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 25
 * @description OpenFeign Custom Error Decoder
 */
@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(final String methodKey, final Response response) {
        log.warn("statusCode[{}], methodKey[{}]", response.status(), methodKey);

        return switch (response.status()) {
            case 400 -> new CustomException("bad request", 400);
            case 404 -> new CustomException("not found", 404);
            case 500 -> new CustomException("internal server error", 500);
            default -> new CustomException("feign client call error");
        };
    }
}
