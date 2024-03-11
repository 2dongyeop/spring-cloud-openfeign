package io.dongvelop.responseserver.payload.request;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 11
 * @description 공통 요청 형태
 */
public record CommonRequest(
        String writer,
        String message) {
}
