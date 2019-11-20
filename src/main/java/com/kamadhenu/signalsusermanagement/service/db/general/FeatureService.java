package com.kamadhenu.signalsusermanagement.service.db.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Feature;

import java.util.List;
import java.util.Optional;

/**
 * Feature interface
 */
public interface FeatureService {

    /**
     * Get feature
     *
     * @param id
     * @return
     */
    Optional<Feature> get(Integer id);

    /**
     * Edit feature
     *
     * @param feature
     * @return
     */
    Feature edit(Feature feature);

    /**
     * Delete feature
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * Get all feature basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<Feature> getAll(Integer pageNumber, Integer pageSize);

    /**
     * Get all feature
     *
     * @return
     */
    List<Feature> getAll();

    /**
     * Get feature count
     *
     * @return
     */
    Long count();
}

