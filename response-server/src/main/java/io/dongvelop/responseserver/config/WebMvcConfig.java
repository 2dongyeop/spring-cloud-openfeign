package io.dongvelop.responseserver.config;

import io.dongvelop.responseserver.common.HeaderExtractFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 11
 * @description : 헤더 추출을 위한 필터 등록 설정 클래스
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${cors.allowed.mapping}")
    private String corsMapping;

    @Value("${cors.allowed.origins}")
    private String[] corsOrigins;

    @Value("${cors.allowed.headers}")
    private String[] corsHeaders;

    @Value("${cors.allowed.methods}")
    private String[] corsMethods;

    @Bean
    public FilterRegistrationBean<HeaderExtractFilter> logFilter() {
        return new FilterRegistrationBean<>(new HeaderExtractFilter());
    }
}
