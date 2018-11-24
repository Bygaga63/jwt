package com.home.securityrest.security.model;

import com.home.securityrest.model.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Token {
    @Id
    @GeneratedValue
    private Long id;
    private String value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
