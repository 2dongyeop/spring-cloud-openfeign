package io.dongvelop.requestserver.common;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 11
 * @description RestTemplate 사용 시 요청에 헤더를 설정하는 인터셉터
 */
@RequiredArgsConstructor
public class RequestHeaderInterceptor implements ClientHttpRequestInterceptor {

    private final String headerName;
    private final String headerValue;

    /**
     * RestTemplate 사용 시 요청에 헤더를 설정하는 메서드
     *
     * @param request   : HTTP 요청
     * @param body      : 요청 바디
     * @param execution : 요청 실행 클래스
     * @return : HTTP 응답
     * @throws IOException
     */
    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {
        final HttpRequest wrapper = new HttpRequestWrapper(request);
        wrapper.getHeaders().set(headerName, headerValue);

        return execution.execute(wrapper, body);
    }
}
