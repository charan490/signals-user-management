package com.kamadhenu.signalsusermanagement.service.db.security;

import com.kamadhenu.signalsusermanagement.model.db.security.User;
import com.kamadhenu.signalsusermanagement.model.db.security.UserGroups;

import java.util.List;
import java.util.Optional;

/**
 * UserGroups interface
 */
public interface UserGroupsService {

    /**
     * Get user_groups
     *
     * @param id
     * @return
     */
    Optional<UserGroups> get(Integer id);

    /**
     * Edit user_groups
     *
     * @param user_groups
     * @return
     */
    UserGroups edit(UserGroups user_groups);

    /**
     * Delete user_groups
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * Get all user_groups basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<UserGroups> getAll(Integer pageNumber, Integer pageSize);

    /**
     * Get all user_groups
     *
     * @return
     */
    List<UserGroups> getAll();

    /**
     * Get user_groups count
     *
     * @return
     */
    Long count();

    /**
     * Get all user_groups basis user
     *
     * @param user
     * @return list of userGroups
     */
    List<UserGroups> getByUser(User user);
}

