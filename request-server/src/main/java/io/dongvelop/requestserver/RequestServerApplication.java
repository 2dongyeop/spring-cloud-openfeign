package io.dongvelop.requestserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class RequestServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RequestServerApplication.class, args);
    }

}
