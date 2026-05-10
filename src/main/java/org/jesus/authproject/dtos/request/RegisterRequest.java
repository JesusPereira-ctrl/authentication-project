package org.jesus.authproject.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    @NotEmpty(message = "The firstName cannot be empty")
    @Length(min = 3, message = "min 3 characters")
    private String firstName;

    @NotEmpty(message = "The lastName cannot be empty")
    @Length(min = 3, message = "min 3 characters")
    private String lastName;

    @NotEmpty(message = "The email cannot be empty")
    @Email(message = "The email is invalid")
    private String email;

    @NotEmpty(message = "The password cannot be empty")
    @Length(min = 6, message = "Min 6 characters")
    private String password;
}
