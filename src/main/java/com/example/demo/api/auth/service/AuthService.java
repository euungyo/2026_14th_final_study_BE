package com.example.demo.api.auth.service;

import com.example.demo.api.auth.dto.request.LoginRequestDTO;
import com.example.demo.api.auth.dto.request.SignupRequestDTO;
import com.example.demo.api.auth.dto.response.LoginResponseDTO;
import com.example.demo.api.auth.dto.response.SignupResponseDTO;
import com.example.demo.api.user.entity.UserEntity;
import com.example.demo.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO) {

        UserEntity user = UserEntity.builder()
                .email(signupRequestDTO.getEmail())
                .password(signupRequestDTO.getPassword())
                .nickname(signupRequestDTO.getNickname())
                .build();

        UserEntity savedUser = userRepository.save(user);

        return new SignupResponseDTO(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getNickname()
        );
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        return new LoginResponseDTO(
                "sample-access-token",
                "Bearer"
        );
    }
}
