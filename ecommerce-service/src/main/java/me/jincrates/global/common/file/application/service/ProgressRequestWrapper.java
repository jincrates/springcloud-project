package me.jincrates.global.common.file.application.service;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import me.jincrates.global.common.file.application.service.response.Progress;

import java.io.IOException;

public class ProgressRequestWrapper extends HttpServletRequestWrapper {
    private final Progress progress;

    public ProgressRequestWrapper(HttpServletRequest request, Progress progress) {
        super(request);
        this.progress = progress;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new ProgressInputStream(super.getInputStream(), progress);
    }
}
