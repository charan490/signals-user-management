package com.kamadhenu.signalsusermanagement.service.db.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Groups;
import com.kamadhenu.signalsusermanagement.model.db.general.GroupsPlaybookFeature;
import com.kamadhenu.signalsusermanagement.model.db.general.PlaybookFeature;
import com.kamadhenu.signalsusermanagement.repository.general.PlaybookFeatureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Impl class for table playbook_feature.
 *
 * @copyright Kamadhenu
 */

@Service
public class PlaybookFeatureServiceImpl implements PlaybookFeatureService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PlaybookFeatureRepository playbookFeatureRepository;

    @Autowired
    private GroupsPlaybookFeatureService groupsPlaybookFeatureService;

    /**
     * Get playbook_feature
     *
     * @param id
     * @return
     */
    @Override
    public Optional<PlaybookFeature> get(Integer id) {
        return playbookFeatureRepository.findById(id);
    }

    /**
     * Edit playbook_feature
     *
     * @param playbookFeature
     * @return
     */
    @Override
    public PlaybookFeature edit(PlaybookFeature playbookFeature) {
        return playbookFeatureRepository.save(playbookFeature);
    }

    /**
     * Delete playbook_feature
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        playbookFeatureRepository.deleteById(id);
    }

    /**
     * Get all playbook_feature basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public List<PlaybookFeature> getAll(Integer pageNumber, Integer pageSize) {
        return playbookFeatureRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    /**
     * Get all playbook_feature
     *
     * @return
     */
    @Override
    public List<PlaybookFeature> getAll() {
        return playbookFeatureRepository.findAll();
    }

    /**
     * Get playbook_feature count
     *
     * @return
     */
    public Long count() {
        return playbookFeatureRepository.count();
    }

    /**
     * Get all playbook_feature basis of groups
     *
     * @return
     */
    public List<Integer> getByGroups(Groups groups) {
        List<Integer> playbookFeatures = new ArrayList<>();
        List<GroupsPlaybookFeature> groupsPlaybookFeatures = groupsPlaybookFeatureService.getByGroups(groups);
        if (!groupsPlaybookFeatures.isEmpty()) {
            for (GroupsPlaybookFeature groupsPlaybookFeature : groupsPlaybookFeatures) {
                playbookFeatures.add(groupsPlaybookFeature.getPlaybookFeature());
            }
        }
        return playbookFeatures;
    }
}
