package com.home.securityrest.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.home.securityrest.security.model.Role;
import com.home.securityrest.security.model.Status;
import com.home.securityrest.security.model.Token;
import com.home.securityrest.views.UserView;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    @JsonView(UserView.UserResponse.class)
    private Long id;

    @JsonView(UserView.UserResponse.class)
    private String name;

    @JsonView(UserView.UserResponse.class)
    private Integer age;

    @JsonView(UserView.UserResponse.class)
    private String login;

    @JsonView(UserView.AdminResponse.class)
    private String hashPassword;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @JsonView(UserView.AdminResponse.class)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @JsonView(UserView.AdminResponse.class)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "user")
    @JsonView(UserView.AdminResponse.class)
    private List<Token> tokens;

}
