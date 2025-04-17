package com.example.authservice;

import com.example.authservice.dto.RegisterRequest;
import com.example.authservice.entity.User;
import com.example.authservice.repo.UserRepository;
import com.example.authservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldRegisterUserWhenUsernameNotExists() {
        String username = "testuser";
        String password = "password";

        when(userRepository.existsByUsername(username)).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User savedUser = userService.register(new RegisterRequest(username, password));

        assertEquals(username, savedUser.getUsername());
//        assertEquals(password, savedUser.getPassword());
    }

    @Test
    void shouldThrowExceptionWhenUsernameExists() {
        String username = "testuser";

        when(userRepository.existsByUsername(username)).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.register(new RegisterRequest(username, "password")));
    }
}