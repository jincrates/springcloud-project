package me.jincrates.authservice.api.controller.request;

import lombok.Getter;

@Getter
public class AuthRequest {

    private String username;
    private String password;

    private AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
