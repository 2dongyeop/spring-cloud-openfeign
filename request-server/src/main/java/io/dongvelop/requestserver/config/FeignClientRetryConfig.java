package io.dongvelop.requestserver.config;

import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 24
 * @description FeignClient 전용 Retry 설정 클래스
 */
public class FeignClientRetryConfig {

    @Value("${spring.cloud.openfeign.retry.openFeignClient.period}")
    private Long period;
    @Value("${spring.cloud.openfeign.retry.openFeignClient.duration}")
    private Long duration;
    @Value("${spring.cloud.openfeign.retry.openFeignClient.maxAttempt}")
    private int maxAttempt;

    /**
     * openFeignClient 전용 Retryer
     * {period} 의 간격으로 시작해 최대 {duration}의 간격으로 증가
     * 최대 {maxAttempt}번 재시도한다.
     */
    @Bean
    Retryer.Default openFeinClientRetryer() {
        // 0.1초의 간격으로 시작해 최대 3초의 간격으로 점점 증가하며, 최대5번 재시도한다.
        return new Retryer.Default(
                period,                               // default : 100
                TimeUnit.SECONDS.toMillis(duration),  // default : 1L
                maxAttempt                            // default : 5
        );
    }
}
