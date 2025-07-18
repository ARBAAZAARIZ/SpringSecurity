package com.arbaaz.SpringSecurity9.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class RequestValidationAfterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("RequestValidationAfterFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
