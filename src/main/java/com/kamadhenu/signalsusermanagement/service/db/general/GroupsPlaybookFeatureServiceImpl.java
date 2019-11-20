package com.kamadhenu.signalsusermanagement.service.db.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Groups;
import com.kamadhenu.signalsusermanagement.model.db.general.GroupsPlaybookFeature;
import com.kamadhenu.signalsusermanagement.repository.general.GroupsPlaybookFeatureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Impl class for table groups_playbook_feature.
 *
 * @copyright Kamadhenu
 */

@Service
public class GroupsPlaybookFeatureServiceImpl implements GroupsPlaybookFeatureService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GroupsPlaybookFeatureRepository groupsPlaybookFeatureRepository;

    /**
     * Get groups_playbook_feature
     *
     * @param id
     * @return
     */
    @Override
    public Optional<GroupsPlaybookFeature> get(Integer id) {
        return groupsPlaybookFeatureRepository.findById(id);
    }

    /**
     * Edit groups_playbook_feature
     *
     * @param groupsPlaybookFeature
     * @return
     */
    @Override
    public GroupsPlaybookFeature edit(GroupsPlaybookFeature groupsPlaybookFeature) {
        return groupsPlaybookFeatureRepository.save(groupsPlaybookFeature);
    }

    /**
     * Delete groups_playbook_feature
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        groupsPlaybookFeatureRepository.deleteById(id);
    }

    /**
     * Get all groups_playbook_feature basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public List<GroupsPlaybookFeature> getAll(Integer pageNumber, Integer pageSize) {
        return groupsPlaybookFeatureRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    /**
     * Get all groups_playbook_feature
     *
     * @return
     */
    @Override
    public List<GroupsPlaybookFeature> getAll() {
        return groupsPlaybookFeatureRepository.findAll();
    }

    /**
     * Get groups_playbook count
     *
     * @return
     */
    public Long count() {
        return groupsPlaybookFeatureRepository.count();
    }

    /**
     * Get all groups_playbook_feature basis of groups
     *
     * @return
     */
    public List<GroupsPlaybookFeature> getByGroups(Groups groups) {
        return groupsPlaybookFeatureRepository.findByGroups(groups.getId());
    }
}
