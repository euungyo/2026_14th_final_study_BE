package com.example.demo.api.auth.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDTO {
    private String email;
    private String nickname;
    private String password;
}
