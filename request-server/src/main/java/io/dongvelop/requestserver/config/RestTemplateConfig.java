package io.dongvelop.requestserver.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 11
 * @description : RestTemplate 설정 클래스
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @Qualifier("restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
