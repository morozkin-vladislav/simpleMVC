package org.example.web.dto;

import org.springframework.stereotype.Service;

@Service
public class LoginForm {
    private String username;
    private String password;


    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginForm() {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
