package com.kamadhenu.signalsusermanagement.repository.general;

import com.kamadhenu.signalsusermanagement.model.db.general.GroupsPlaybookFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * GroupPlaybookFeature repository class
 */
@Repository
public interface GroupsPlaybookFeatureRepository extends JpaRepository<GroupsPlaybookFeature, Integer> {

    /**
     * Get groupsPlaybookFeature basis of groups
     *
     * @param groups
     * @return
     */
    @Query(value = "SELECT gpf.* from groups_playbook_feature gpf  WHERE gpf.groups = ?1", nativeQuery = true)
    List<GroupsPlaybookFeature> findByGroups(Integer groups);
}
