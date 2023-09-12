package me.jincrates.api.global.common.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PageResponse<T> {
    private int pageNo;
    private boolean hasNext;
    private List<T> contents;

    @Builder
    public PageResponse(int pageNo, boolean hasNext, List<T> contents) {
        this.pageNo = pageNo;
        this.hasNext = hasNext;
        this.contents = contents;
    }
}
