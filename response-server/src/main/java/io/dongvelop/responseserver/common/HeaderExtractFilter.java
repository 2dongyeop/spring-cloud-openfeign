package io.dongvelop.responseserver.common;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2024. 03. 11
 * @description 헤더 정보 출력 Filter
 */
@Slf4j
public class HeaderExtractFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(CommonConst.AUTHORIZATION);
        final String contentType = request.getHeader(CommonConst.CONTENT_TYPE_KEY);

        log.info("============ Header Start ============");
        log.info("authorization[{}]", authorization);
        log.info("contentType[{}]", contentType);
        log.info("============= Header End =============");

        filterChain.doFilter(request, response);
    }
}
