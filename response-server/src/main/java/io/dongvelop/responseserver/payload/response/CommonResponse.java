package io.dongvelop.responseserver.payload.response;

import io.dongvelop.responseserver.payload.request.CommonRequest;

import java.time.LocalDateTime;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 11
 * @description 공통 응답 클래스
 */
public record CommonResponse(
        String writer,
        String message,
        LocalDateTime responseTime
) {
    public static CommonResponse of(final CommonRequest request) {
        return new CommonResponse(
                request.writer(),
                request.message(),
                LocalDateTime.now()
        );
    }
}
