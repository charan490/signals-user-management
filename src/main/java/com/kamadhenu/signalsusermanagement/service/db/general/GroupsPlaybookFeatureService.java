package com.kamadhenu.signalsusermanagement.service.db.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Groups;
import com.kamadhenu.signalsusermanagement.model.db.general.GroupsPlaybookFeature;

import java.util.List;
import java.util.Optional;

/**
 * GroupsPlaybookFeature interface
 */
public interface GroupsPlaybookFeatureService {

    /**
     * Get groups_playbook_feature
     *
     * @param id
     * @return
     */
    Optional<GroupsPlaybookFeature> get(Integer id);

    /**
     * Edit groups_playbook_feature
     *
     * @param groupPlaybook
     * @return
     */
    GroupsPlaybookFeature edit(GroupsPlaybookFeature groupPlaybook);

    /**
     * Delete groups_playbook_feature
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * Get all groups_playbook_feature basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<GroupsPlaybookFeature> getAll(Integer pageNumber, Integer pageSize);

    /**
     * Get all groups_playbook_feature
     *
     * @return
     */
    List<GroupsPlaybookFeature> getAll();

    /**
     * Get groups_playbook_feature count
     *
     * @return
     */
    Long count();

    /**
     * Get all groups_playbook_feature basis of groups
     *
     * @return
     */
    List<GroupsPlaybookFeature> getByGroups(Groups groups);
}

