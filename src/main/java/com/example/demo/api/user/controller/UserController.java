package com.example.demo.api.user.controller;

import com.example.demo.api.user.dto.UserResponseDTO;
import com.example.demo.api.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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
}
