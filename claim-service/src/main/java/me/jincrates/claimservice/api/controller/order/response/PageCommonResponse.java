package me.jincrates.claimservice.api.controller.order.response;

import java.util.List;
import lombok.Getter;

@Getter
public class PageCommonResponse<T> {

    private long totalPages;
    private List<T> contents;

    private PageCommonResponse(long totalPages, List<T> contents) {
        this.totalPages = totalPages;
        this.contents = contents;
    }

    public static <T> PageCommonResponse<T> of(List<T> contents) {
        return new PageCommonResponse<>(contents.size(), contents);
    }
}
