package com.kamadhenu.signalsusermanagement.model.api.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Api Playbook Feature Response model
 */
@Data
@ToString
@EqualsAndHashCode
public class PlaybookFeatureResponse {

    private String playbook;

    private String feature;
}
