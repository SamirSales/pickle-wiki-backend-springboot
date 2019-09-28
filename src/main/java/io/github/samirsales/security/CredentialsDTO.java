package io.github.samirsales.security;

import java.io.Serializable;

public class CredentialsDTO implements Serializable {

    private String login;
    private String password;

    public CredentialsDTO(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
