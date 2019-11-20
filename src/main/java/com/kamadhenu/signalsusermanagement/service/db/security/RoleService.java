package com.kamadhenu.signalsusermanagement.service.db.security;

import com.kamadhenu.signalsusermanagement.model.db.security.Role;

import java.util.List;
import java.util.Optional;

/**
 * Role interface
 */
public interface RoleService {

    /**
     * Get role
     *
     * @param id
     * @return
     */
    Optional<Role> get(Integer id);

    /**
     * Edit role
     *
     * @param role
     * @return
     */
    Role edit(Role role);

    /**
     * Delete role
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * Get all role basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<Role> getAll(Integer pageNumber, Integer pageSize);

    /**
     * Get all role
     *
     * @return
     */
    List<Role> getAll();

    /**
     * Get role count
     *
     * @return
     */
    Long count();
}

