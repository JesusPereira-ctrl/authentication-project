package org.jesus.authproject.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class LoginRequest {

    @NotEmpty(message = "the email cannot be blank")
    @Email(message = "format email invalid")
    private String email;

    @NotEmpty(message = "the password cannot be blank")
    @Length(min = 6, message = "Min 6 characters")
    private String password;
}
