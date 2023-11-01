package me.jincrates.global.common.enumtype;

import lombok.Getter;

@Getter
public enum FileBucket {
    MEMBER("member", "사용자"),
    STORE("store", "상점"),
    PRODUCT("product", "상품"),
    REVIEW("review", "리뷰"),
    COMMUNITY("community", "커뮤니티"),
    ;

    private final String value;
    private final String description;

    FileBucket(String value, String description) {
        this.value = value;
        this.description = description;
    }
}
