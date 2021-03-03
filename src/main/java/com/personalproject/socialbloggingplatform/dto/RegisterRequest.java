package com.personalproject.socialbloggingplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "RegisterRequest must have a name")
    private String username;

    @NotBlank( message = "RegisterRequest must have an email")
    @Email
    private String email;

    @NotBlank( message = "RegisterRequest's password can not be empty")
    private String password;

}
