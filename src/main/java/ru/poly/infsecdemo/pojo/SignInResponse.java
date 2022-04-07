package ru.poly.infsecdemo.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SignInResponse {
    private String subject;
    private Collection<?> authorities;
}
