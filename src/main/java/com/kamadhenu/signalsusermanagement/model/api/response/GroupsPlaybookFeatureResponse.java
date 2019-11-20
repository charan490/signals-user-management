package com.kamadhenu.signalsusermanagement.model.api.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Api Group Playbook Feature Response model
 */
@Data
@ToString
@EqualsAndHashCode
public class GroupsPlaybookFeatureResponse {

    private String group;

    private List<PlaybookFeatureResponse> playbookFeatureResponses;
}
