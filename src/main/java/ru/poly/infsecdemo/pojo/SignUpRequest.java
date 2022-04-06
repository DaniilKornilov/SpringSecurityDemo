package ru.poly.infsecdemo.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest {
    private String username;
    private String email;
    private Set<String> roles;
    private String password;
}
