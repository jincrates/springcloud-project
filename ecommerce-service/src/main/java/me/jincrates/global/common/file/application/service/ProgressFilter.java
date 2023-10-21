package me.jincrates.global.common.file.application.service;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import me.jincrates.global.common.file.application.service.response.Progress;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProgressFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        Progress progress = new Progress();
        session.setAttribute("progress", progress);

        chain.doFilter(new ProgressRequestWrapper(req, progress), response);
    }
}