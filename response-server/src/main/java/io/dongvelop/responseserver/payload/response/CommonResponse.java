package io.dongvelop.responseserver.payload.response;

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
}
