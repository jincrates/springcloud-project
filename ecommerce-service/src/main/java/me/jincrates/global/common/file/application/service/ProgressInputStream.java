package me.jincrates.global.common.file.application.service;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import me.jincrates.global.common.file.application.service.response.Progress;

import java.io.IOException;

public class ProgressInputStream extends ServletInputStream {
    private final ServletInputStream sourceStream;
    private final Progress progress;

    public ProgressInputStream(ServletInputStream sourceStream, Progress progress) {
        this.sourceStream = sourceStream;
        this.progress = progress;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener listener) {

    }

    @Override
    public int read() throws IOException {
        int value = sourceStream.read();
        if (value != -1) {
            progress.setBytesRead(progress.getBytesRead() + 1);
            progress.calculatePercent();
        }
        return value;
    }
}
