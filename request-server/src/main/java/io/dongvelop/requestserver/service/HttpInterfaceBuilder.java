package io.dongvelop.requestserver.service;

import lombok.experimental.UtilityClass;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 16
 * @description HttpInterface 생성 클래스
 */
@UtilityClass
public class HttpInterfaceBuilder {

    public static <T> T builder(final RestClient restClient, Class<T> httpInterfaceType) {
        final RestClientAdapter adapter = RestClientAdapter.create(restClient);
        final HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(httpInterfaceType);
    }
}
