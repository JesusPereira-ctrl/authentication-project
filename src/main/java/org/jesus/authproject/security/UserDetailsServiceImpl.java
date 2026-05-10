package org.jesus.authproject.security;

import lombok.RequiredArgsConstructor;
import org.jesus.authproject.entities.UserEntity;
import org.jesus.authproject.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));

        List<SimpleGrantedAuthority> authorities = userEntity.getRoles().stream().map(role -> {
            return new SimpleGrantedAuthority("ROLE_".concat(role.getName().toString()));
        }).toList();

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}
