package com.example.demo.api.auth.service;

import com.example.demo.api.auth.dto.request.LoginRequestDTO;
import com.example.demo.api.auth.dto.request.SignupRequestDTO;
import com.example.demo.api.auth.dto.response.LoginResponseDTO;
import com.example.demo.api.auth.dto.response.SignupResponseDTO;
import com.example.demo.api.user.entity.UserEntity;
import com.example.demo.api.user.repository.UserRepository;
import com.example.demo.global.exception.CustomException;
import com.example.demo.global.exception.ErrorCode;
import com.example.demo.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO) {

        if (userRepository.existsByEmail(signupRequestDTO.getEmail())) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        UserEntity user = UserEntity.builder()
                .email(signupRequestDTO.getEmail())
                .password(passwordEncoder.encode(signupRequestDTO.getPassword()))
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

        UserEntity user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        String accessToken = jwtProvider.createAccessToken(user.getId());

        return new LoginResponseDTO(
                accessToken,
                "Bearer"
        );
    }
}
