package com.home.securityrest.transfer;

import lombok.Data;

@Data
public class UserForm {
    private String name;
    private Integer age;
    private String login;
    private String password;
}
