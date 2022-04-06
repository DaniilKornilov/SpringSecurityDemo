package ru.poly.infsecdemo.service;

import ru.poly.infsecdemo.entity.User;
import ru.poly.infsecdemo.pojo.JwtResponse;
import ru.poly.infsecdemo.pojo.SignInRequest;
import ru.poly.infsecdemo.pojo.SignUpRequest;

public interface AuthService {
    JwtResponse authUser(SignInRequest signInRequest);

    User registerUser(SignUpRequest signUpRequest);
}
