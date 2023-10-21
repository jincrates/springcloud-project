package me.jincrates.global.common.file.application.service.response;

import lombok.Data;

@Data
public class Progress {
    private long bytesRead;
    private long contentLength;
    private int percent;

    public Progress(long bytesRead, long contentLength) {
        this.bytesRead = bytesRead;
        this.contentLength = contentLength;
        calculatePercent();
    }

    private void calculatePercent() {
        percent = (int) (bytesRead * 100 / contentLength);
    }
}