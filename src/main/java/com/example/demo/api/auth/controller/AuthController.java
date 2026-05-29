package com.example.demo.api.auth.controller;

import com.example.demo.api.auth.dto.request.LoginRequestDTO;
import com.example.demo.api.auth.dto.request.SignupRequestDTO;
import com.example.demo.api.auth.dto.response.LoginResponseDTO;
import com.example.demo.api.auth.dto.response.SignupResponseDTO;
import com.example.demo.api.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name="Auth", description="인증 api")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(
            @RequestBody SignupRequestDTO signupRequestDTO) {

        SignupResponseDTO response = authService.signup(signupRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO loginRequestDTO){

        LoginResponseDTO response = authService.login(loginRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
