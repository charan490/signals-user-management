package com.kamadhenu.signalsusermanagement.service.db.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Groups;
import com.kamadhenu.signalsusermanagement.model.db.general.PlaybookFeature;

import java.util.List;
import java.util.Optional;

/**
 * PlaybookFeature interface
 */
public interface PlaybookFeatureService {

    /**
     * Get playbook_feature
     *
     * @param id
     * @return
     */
    Optional<PlaybookFeature> get(Integer id);

    /**
     * Edit playbook_feature
     *
     * @param playbookFeature
     * @return
     */
    PlaybookFeature edit(PlaybookFeature playbookFeature);

    /**
     * Delete playbook_feature
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * Get all playbook_feature basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<PlaybookFeature> getAll(Integer pageNumber, Integer pageSize);

    /**
     * Get all playbook_feature
     *
     * @return
     */
    List<PlaybookFeature> getAll();

    /**
     * Get playbook_feature count
     *
     * @return
     */
    Long count();

    /**
     * Get all playbook_feature basis of groups
     *
     * @return
     */
    List<Integer> getByGroups(Groups groups);
}

