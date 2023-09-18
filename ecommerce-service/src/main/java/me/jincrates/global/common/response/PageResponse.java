package me.jincrates.global.common.response;

import java.util.List;
import lombok.Getter;

@Getter
public class PageResponse<T> {

    private int pageNo;
    private boolean hasNext;
    private List<T> contents;

    private PageResponse(int pageNo, boolean hasNext, List<T> contents) {
        this.pageNo = pageNo;
        this.hasNext = hasNext;
        this.contents = contents;
    }

    public static <T> PageResponse<T> create(int pageNo, int pageSize, List<T> contents) {
        return new PageResponse<>(
            pageNo,
            contents.size() > pageSize,
            contents.subList(0, Math.min(contents.size(), pageSize))
        );
    }
}
