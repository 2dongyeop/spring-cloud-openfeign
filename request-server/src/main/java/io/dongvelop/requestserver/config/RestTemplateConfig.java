package io.dongvelop.requestserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 11
 * @description : RestTemplate 설정 클래스
 */
@Slf4j
@Configuration
public class RestTemplateConfig {

    @Value("${restTemplate.connectTimeOut}")
    private Long connectTimeOut;
    @Value("${restTemplate.readTimeOut}")
    private Long readTimeOut;

    @Bean
    @Qualifier("restTemplate")
    public RestTemplate restTemplate(final RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(connectTimeOut))  // 외부 API 서버에 연결 요청 시간
                .setReadTimeout(Duration.ofSeconds(readTimeOut))        // 외부 API 서버로부터 데이터를 읽어오는 시간
                .build();
    }
}
