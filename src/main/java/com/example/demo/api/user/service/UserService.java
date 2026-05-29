package com.example.demo.api.user.service;

import com.example.demo.api.user.dto.UserResponseDTO;
import com.example.demo.api.user.entity.UserEntity;
import com.example.demo.api.user.repository.UserRepository;
import com.example.demo.global.exception.CustomException;
import com.example.demo.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO getMyInfo(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));

        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getNickname()
        );
    }
}
