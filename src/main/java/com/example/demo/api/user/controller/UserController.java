package com.example.demo.api.user.controller;

import com.example.demo.api.user.dto.UserResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name="User", description="user api")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMyInfo() {

        UserResponseDTO response = new UserResponseDTO(
                1L,
                "Test@test.com",
                "이은교"
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
