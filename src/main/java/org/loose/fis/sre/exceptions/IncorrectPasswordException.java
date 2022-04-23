package org.loose.fis.sre.exceptions;

import javax.security.auth.login.AccountException;

public class IncorrectPasswordException extends AccountException{

    private String password;

    public IncorrectPasswordException(String password) {
        super(String.format("The password is incorrect!"));
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
