package com.kamadhenu.signalsusermanagement.model.domain.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * CSV User model
 */
@Data
@ToString
@EqualsAndHashCode
public class UserModel {

    private Integer id;

    private String name;

    private String email;

    private Date expirationDate;

    private String password;

    private String role;

    private String userType;

    private String company;
}
