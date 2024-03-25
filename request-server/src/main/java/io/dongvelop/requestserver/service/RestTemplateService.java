package io.dongvelop.requestserver.service;

import io.dongvelop.requestserver.common.CommonConst;
import io.dongvelop.requestserver.common.RequestHeaderInterceptor;
import io.dongvelop.requestserver.payload.request.CommonRequest;
import io.dongvelop.requestserver.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 11
 * @description RestTemplate 사용 예제 Service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RestTemplateService {

    @Value("${url.server.response.endpoint}")
    private String responseServerUrl;
    @Value("${url.server.response.retry}")
    private String retryRequestPath;

    private final RestTemplate restTemplate;

    /**
     * RestTemplate 으로 응답 서버에 요청보내기 <br/>
     * - Retry를 제공하지 않음. <br/>
     * - 비즈니스 코드에 지저분한 설정 코드들이 섞임. <br/>
     * - OpenFeign에 비해 제공되는 로그가 불친절함
     *
     * @param request : 공통 요청 형태
     * @return : 공통 응답 형태
     */
    public CommonResponse send(final CommonRequest request) {
        log.info("request[{}]", request);
        settingHeader();
        return restTemplate.postForObject(responseServerUrl, request, CommonResponse.class);
    }

    /**
     * RestTemplate은 Retry를 지원하지 않아, 아래처럼 Spring-Retry를 이용하여 구현
     */
    @Retryable(maxAttempts = 3)
    public CommonResponse sendRetry(final CommonRequest request) {
        log.info("request[{}]", request);
        settingHeader();
        return restTemplate.postForObject(responseServerUrl + retryRequestPath, request, CommonResponse.class);
    }

    private void settingHeader() {
        /* Header에 필요한 정보 삽입 */
        final ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new RequestHeaderInterceptor(CommonConst.AUTHORIZATION, CommonConst.BEARER));
        interceptors.add(new RequestHeaderInterceptor(CommonConst.CONTENT_TYPE_KEY, MediaType.APPLICATION_JSON_VALUE));
        restTemplate.setInterceptors(interceptors);
    }
}
