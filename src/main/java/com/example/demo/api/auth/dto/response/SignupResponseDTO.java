package com.example.demo.api.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupResponseDTO {

    private Long userId;
    private String email;
    private String nickname;
}
