package com.kamadhenu.signalsusermanagement.model.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
@EqualsAndHashCode
public class ApiUser {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
