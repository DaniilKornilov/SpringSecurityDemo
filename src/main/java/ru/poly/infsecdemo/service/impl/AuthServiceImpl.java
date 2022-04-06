package ru.poly.infsecdemo.service.impl;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.poly.infsecdemo.entity.Role;
import ru.poly.infsecdemo.entity.User;
import ru.poly.infsecdemo.entity.enumeration.RoleEnum;
import ru.poly.infsecdemo.exception.InvalidSignUpRequestBody;
import ru.poly.infsecdemo.pojo.JwtResponse;
import ru.poly.infsecdemo.pojo.SignInRequest;
import ru.poly.infsecdemo.pojo.SignUpRequest;
import ru.poly.infsecdemo.repository.RoleRepository;
import ru.poly.infsecdemo.repository.UserRepository;
import ru.poly.infsecdemo.security.jwt.JwtUtils;
import ru.poly.infsecdemo.service.AuthService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private static final String USERNAME_TAKEN_MESSAGE = "Error: Username: %s already taken";

    private static final String USERNAME_INVALID_MESSAGE = "Error: Username is invalid";

    private static final String EMAIL_TAKEN_MESSAGE = "Error: Email: %s already taken";

    private static final String EMAIL_INVALID_MESSAGE = "Error: Email is invalid";

    private static final String PASSWORD_INVALID_MESSAGE = "Error: Password is invalid";

    private static final String ROLES_INVALID_MESSAGE = "Error: Roles are invalid";

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository,
                           AuthenticationManager authenticationManager,
                           JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }


    @Override
    public JwtResponse authUser(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsername(),
                        signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public User registerUser(SignUpRequest signUpRequest) {
        validateSignUpRequest(signUpRequest);
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        Set<Role> roles = new HashSet<>();
        signUpRequest.getRoles().forEach(r -> {
            Role role = roleRepository.findByRoleEnum(RoleEnum.get(r));
            roles.add(role);
        });
        user.setRoles(roles);
        return userRepository.save(user);
    }

    private void validateSignUpRequest(SignUpRequest signUpRequest) {
        String username = signUpRequest.getUsername();
        validateUsername(username);

        String email = signUpRequest.getEmail();
        validateEmail(email);

        String password = signUpRequest.getPassword();
        validatePassword(password);

        Set<String> roles = signUpRequest.getRoles();
        validateRoles(roles);
    }

    private void validateUsername(String username) {
        if (username == null || username.length() < 2 || username.length() > 20) {
            throw new InvalidSignUpRequestBody(USERNAME_INVALID_MESSAGE);
        }
        if (userRepository.existsByUsername(username)) {
            throw new InvalidSignUpRequestBody(String.format(USERNAME_TAKEN_MESSAGE, username));
        }
    }

    private void validateEmail(String email) {
        if (email == null || !EmailValidator.getInstance().isValid(email)) {
            throw new InvalidSignUpRequestBody(EMAIL_INVALID_MESSAGE);
        }
        if (userRepository.existsByEmail(email)) {
            throw new InvalidSignUpRequestBody(String.format(EMAIL_TAKEN_MESSAGE, email));
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 2 || password.length() > 20) {
            throw new InvalidSignUpRequestBody(PASSWORD_INVALID_MESSAGE);
        }
    }

    private void validateRoles(Set<String> roles) {
        if (roles == null) {
            throw new InvalidSignUpRequestBody(ROLES_INVALID_MESSAGE);
        }
        roles.forEach(role -> {
            if (!roleRepository.existsByRoleEnum(RoleEnum.get(role))) {
                throw new InvalidSignUpRequestBody(ROLES_INVALID_MESSAGE);
            }
        });
    }
}
