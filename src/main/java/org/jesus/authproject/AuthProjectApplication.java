package org.jesus.authproject;

import lombok.RequiredArgsConstructor;
import org.jesus.authproject.entities.RoleEntity;
import org.jesus.authproject.entities.RoleEnum;
import org.jesus.authproject.entities.UserEntity;
import org.jesus.authproject.repositories.RoleRepository;
import org.jesus.authproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class AuthProjectApplication implements CommandLineRunner {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${admin.firstName}")
    private String firstName;

    @Value("${admin.lastName}")
    private String lastName;

    @Value("${admin.email}")
    private String email;

    @Value("${admin.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(AuthProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        RoleEntity roleUser = new RoleEntity();
        roleUser.setName(RoleEnum.ROLE_USER);

        RoleEntity roleAdmin = new RoleEntity();
        roleAdmin.setName(RoleEnum.ROLE_ADMIN);

        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);

        UserEntity admin = new UserEntity();
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRoles(Set.of(roleAdmin));
        userRepository.save(admin);
    }
}
