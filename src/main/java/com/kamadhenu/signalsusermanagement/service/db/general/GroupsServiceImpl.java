package com.kamadhenu.signalsusermanagement.service.db.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Groups;
import com.kamadhenu.signalsusermanagement.repository.general.GroupsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Impl class for table groups.
 *
 * @copyright Kamadhenu
 */

@Service
public class GroupsServiceImpl implements GroupsService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GroupsRepository groupsRepository;

    /**
     * Get groups
     *
     * @param id
     * @return
     */
    @Override
    public Optional<Groups> get(Integer id) {
        return groupsRepository.findById(id);
    }

    /**
     * Edit groups
     *
     * @param groups
     * @return
     */
    @Override
    public Groups edit(Groups groups) {
        return groupsRepository.save(groups);
    }

    /**
     * Delete groups
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        groupsRepository.deleteById(id);
    }

    /**
     * Get all groups basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public List<Groups> getAll(Integer pageNumber, Integer pageSize) {
        return groupsRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    /**
     * Get all groups
     *
     * @return
     */
    @Override
    public List<Groups> getAll() {
        return groupsRepository.findAll();
    }

    /**
     * Get groups count
     *
     * @return
     */
    public Long count() {
        return groupsRepository.count();
    }
}
