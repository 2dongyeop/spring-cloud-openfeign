package io.dongvelop.requestserver.common;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 24
 * @description 요청이 수행되기 전, Authorization 헤더를 설정하는 인터셉터
 */
@Slf4j
public class AuthorizationRequestInterceptor implements RequestInterceptor {

    /**
     * 요청이 수행되기 전, Authorization 헤더를 설정
     */
    @Override
    public void apply(final RequestTemplate requestTemplate) {
        requestTemplate.header(CommonConst.AUTHORIZATION, CommonConst.BEARER);
    }
}
