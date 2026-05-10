package org.jesus.authproject.services;

import lombok.RequiredArgsConstructor;
import org.jesus.authproject.dtos.request.LoginRequest;
import org.jesus.authproject.dtos.request.RegisterRequest;
import org.jesus.authproject.dtos.response.AuthResponse;
import org.jesus.authproject.entities.RoleEntity;
import org.jesus.authproject.entities.RoleEnum;
import org.jesus.authproject.entities.UserEntity;
import org.jesus.authproject.exceptions.CredentialsInvalidException;
import org.jesus.authproject.exceptions.NotFoundException;
import org.jesus.authproject.repositories.RoleRepository;
import org.jesus.authproject.repositories.UserRepository;
import org.jesus.authproject.utils.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthResponse register(RegisterRequest registerRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(registerRequest.getFirstName());
        userEntity.setLastName(registerRequest.getLastName());
        userEntity.setEmail(registerRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        RoleEntity role = roleRepository.findByName(RoleEnum.ROLE_USER).orElseThrow(
                () -> new NotFoundException("role not found"));

        userEntity.setRoles(Set.of(role));

        userEntity = userRepository.save(userEntity);

        return new AuthResponse(createToken(userEntity));
    }

    public AuthResponse login(LoginRequest loginRequest) {
        UserEntity userEntity = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new NotFoundException("user with email or password not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())) {
            throw new CredentialsInvalidException("email or password incorrect");
        }

        return new AuthResponse(createToken(userEntity));
    }

    private String createToken(UserEntity userEntity) {
        List<SimpleGrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(r -> {
                    return new SimpleGrantedAuthority(r.getName().toString());
                }).toList();

        Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity.getEmail(), null, authorities);
        return jwtService.createToken(authentication);
    }
}
