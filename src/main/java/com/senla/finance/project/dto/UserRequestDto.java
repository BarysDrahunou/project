package com.senla.finance.project.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

import static com.senla.finance.project.utils.Constants.VALID_EMAIL_MESSAGE;

@Getter
public class UserRequestDto {

    private String firstName;
    private String lastName;
    @Email(message = VALID_EMAIL_MESSAGE)
    private String email;
    private String password;
    private String role;
}
