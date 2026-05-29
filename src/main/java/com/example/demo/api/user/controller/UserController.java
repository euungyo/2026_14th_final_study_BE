package com.example.demo.api.user.controller;

import com.example.demo.api.user.dto.UserResponseDTO;
import com.example.demo.api.user.service.UserService;
import com.example.demo.global.exception.CustomException;
import com.example.demo.global.exception.ErrorCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name="User", description="user api")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMyInfo(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();

        UserResponseDTO response = userService.getMyInfo(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserInfo(
            @PathVariable Long userId,
            Authentication authentication
    ){
        Long loginUserId = (Long) authentication.getPrincipal();

        if(!loginUserId.equals(userId)){
            throw new CustomException(ErrorCode.FORBIDDEN);
        }

        UserResponseDTO response = userService.getMyInfo(userId);

        return ResponseEntity.ok(response);
    }
}
