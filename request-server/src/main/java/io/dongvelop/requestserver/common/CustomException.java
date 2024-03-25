package io.dongvelop.requestserver.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 25
 * @description 사용자 정의 예외 예시
 */
@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private int statusCode;

    public CustomException(final String message, final int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public CustomException(final String message) {
        super(message);
        this.statusCode = 400;
    }
}
