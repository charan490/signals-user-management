package com.kamadhenu.signalsusermanagement.form;

import com.kamadhenu.signalsusermanagement.model.db.general.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Form class for table groups_playbook_feature.
 *
 * @author Suneel Pandey
 * @copyright Kamadhenu
 */
@Data
@ToString
@EqualsAndHashCode
public class GroupsPlaybookFeatureForm {

    @NotNull
    public Groups groups;

    @NotNull
    public List<Integer> playbookFeature;
}
