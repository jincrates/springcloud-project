package me.jincrates.authservice.api.controller;

import lombok.RequiredArgsConstructor;
import me.jincrates.authservice.api.controller.request.AuthRequest;
import me.jincrates.authservice.api.service.TokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping("/auth")
    private String createAuthToken(@RequestBody AuthRequest request) throws Exception {

        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),
                    request.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = tokenProvider.generateToken(userDetails);

            return token;
        } catch (AuthenticationException e) {
            throw new Exception("Invalid username or password", e);
        }
        //{
        //  "accessToken": "asdiofjzxl;ckvjoiasjewr.asdfoiasjdflkajsdf.asdfivjiaosdjf",
        //  "refreshToken": "asdiofjzxl;ckvjoiasjewr.asdfoiasjdflkajsdf.asdfivjiaosdjf"
        //}
    }

    @PostMapping("/auth/token")
    private void refresh() {

        //{
        //  "accessToken": "asdiofjzxl;ckvjoiasjewr.asdfoiasjdflkajsdf.asdfivjiaosdjf",
        //}
    }

}
