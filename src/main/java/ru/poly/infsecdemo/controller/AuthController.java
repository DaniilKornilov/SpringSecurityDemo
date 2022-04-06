package ru.poly.infsecdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.poly.infsecdemo.entity.User;
import ru.poly.infsecdemo.pojo.JwtResponse;
import ru.poly.infsecdemo.pojo.SignInRequest;
import ru.poly.infsecdemo.pojo.SignUpRequest;
import ru.poly.infsecdemo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> authUser(@RequestBody SignInRequest signInRequest) {
        JwtResponse jwtResponse = authService.authUser(signInRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        User user = authService.registerUser(signUpRequest);
        return ResponseEntity.ok(user);
    }
}
