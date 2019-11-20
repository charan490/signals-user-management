package com.kamadhenu.signalsusermanagement.repository.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Feature repository class
 */
@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer> {

}
