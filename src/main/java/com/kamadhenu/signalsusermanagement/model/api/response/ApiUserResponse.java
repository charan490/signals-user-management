package com.kamadhenu.signalsusermanagement.model.api.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Api User Response model
 */
@Data
@ToString
@EqualsAndHashCode
public class ApiUserResponse {

    private String name;

    private String email;

    private Date expirationDate;

    private String role;

    private String userType;

    private String company;

    private List<UserGroupsResponse> userGroupsResponse;
}
