package com.kamadhenu.signalsusermanagement.repository.general;

import com.kamadhenu.signalsusermanagement.model.db.general.PlaybookFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PlaybookFeature repository class
 */
@Repository
public interface PlaybookFeatureRepository extends JpaRepository<PlaybookFeature, Integer> {

}
