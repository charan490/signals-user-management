package com.kamadhenu.signalsusermanagement.model.api.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Api User Group Response model
 */
@Data
@ToString
@EqualsAndHashCode
public class UserGroupsResponse {

    List<GroupsPlaybookFeatureResponse> groupsPlaybookFeatureResponses;
}
