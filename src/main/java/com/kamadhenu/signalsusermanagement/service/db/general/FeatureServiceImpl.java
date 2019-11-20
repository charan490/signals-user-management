package com.kamadhenu.signalsusermanagement.service.db.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Feature;
import com.kamadhenu.signalsusermanagement.repository.general.FeatureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Impl class for table feature.
 *
 * @copyright Kamadhenu
 */

@Service
public class FeatureServiceImpl implements FeatureService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FeatureRepository featureRepository;

    /**
     * Get feature
     *
     * @param id
     * @return
     */
    @Override
    public Optional<Feature> get(Integer id) {
        return featureRepository.findById(id);
    }

    /**
     * Edit feature
     *
     * @param feature
     * @return
     */
    @Override
    public Feature edit(Feature feature) {
        return featureRepository.save(feature);
    }

    /**
     * Delete feature
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        featureRepository.deleteById(id);
    }

    /**
     * Get all feature basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public List<Feature> getAll(Integer pageNumber, Integer pageSize) {
        return featureRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    /**
     * Get all feature
     *
     * @return
     */
    @Override
    public List<Feature> getAll() {
        return featureRepository.findAll();
    }

    /**
     * Get feature count
     *
     * @return
     */
    public Long count() {
        return featureRepository.count();
    }
}
