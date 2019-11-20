package com.kamadhenu.signalsusermanagement.service.db.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Playbook;

import java.util.List;
import java.util.Optional;

/**
 * Playbook interface
 */
public interface PlaybookService {

    /**
     * Get playbook
     *
     * @param id
     * @return
     */
    Optional<Playbook> get(Integer id);

    /**
     * Edit playbook
     *
     * @param playbook
     * @return
     */
    Playbook edit(Playbook playbook);

    /**
     * Delete playbook
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * Get all playbook basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<Playbook> getAll(Integer pageNumber, Integer pageSize);

    /**
     * Get all playbook
     *
     * @return
     */
    List<Playbook> getAll();

    /**
     * Get playbook count
     *
     * @return
     */
    Long count();
}

