package me.jincrates.global.common.file.application.service.response;

import lombok.Data;

@Data
public class Progress {
    private long bytesRead;
    private long contentLength;
    private int percent;

    public void calculatePercent() {
        if (contentLength > 0) {
            percent = (int) (bytesRead * 100 / contentLength);
        }
    }
}