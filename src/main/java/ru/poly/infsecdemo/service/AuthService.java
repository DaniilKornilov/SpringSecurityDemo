package ru.poly.infsecdemo.service;

import ru.poly.infsecdemo.entity.User;
import ru.poly.infsecdemo.pojo.SignUpRequest;

public interface AuthService {
    User registerUser(SignUpRequest signUpRequest);
}
