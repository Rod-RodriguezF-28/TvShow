package com.rodrigo.tvShow.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginUser {

    @Email(message = "El formato del correo electrónico no es válido")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "El formato del correo electrónico no es válido")
    private String emailLogin;
    @Size(min = 3, max = 10, message = "Las password debe tener entre 3 y 20 carecteres")
    private String passwordLogin;

    public LoginUser() {
    }

    public String getEmailLogin() {
        return emailLogin;
    }

    public void setEmailLogin(String emailLogin) {
        this.emailLogin = emailLogin;
    }

    public String getPasswordLogin() {
        return passwordLogin;
    }

    public void setPasswordLogin(String passwordLogin) {
        this.passwordLogin = passwordLogin;
    }
}
