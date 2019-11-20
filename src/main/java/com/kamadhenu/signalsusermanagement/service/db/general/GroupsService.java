package com.kamadhenu.signalsusermanagement.service.db.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Groups;

import java.util.List;
import java.util.Optional;

/**
 * Groups interface
 */
public interface GroupsService {

    /**
     * Get groups
     *
     * @param id
     * @return
     */
    Optional<Groups> get(Integer id);

    /**
     * Edit groups
     *
     * @param groups
     * @return
     */
    Groups edit(Groups groups);

    /**
     * Delete groups
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * Get all groups basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<Groups> getAll(Integer pageNumber, Integer pageSize);

    /**
     * Get all groups
     *
     * @return
     */
    List<Groups> getAll();

    /**
     * Get groups count
     *
     * @return
     */
    Long count();
}

