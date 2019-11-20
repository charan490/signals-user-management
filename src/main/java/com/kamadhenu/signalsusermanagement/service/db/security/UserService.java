package com.kamadhenu.signalsusermanagement.service.db.security;

import com.kamadhenu.signalsusermanagement.model.db.security.User;

import java.util.List;
import java.util.Optional;

/**
 * User interface
 */
public interface UserService {

    /**
     * Get user
     *
     * @param id
     * @return
     */
    Optional<User> get(Integer id);

    /**
     * Edit user
     *
     * @param user
     * @return
     */
    User edit(User user);

    /**
     * Delete user
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * Get all user basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<User> getAll(Integer pageNumber, Integer pageSize);

    /**
     * Get all user
     *
     * @return
     */
    List<User> getAll();

    /**
     * Get user count
     *
     * @return
     */
    Long count();

    /**
     * Get user by email
     *
     * @param email
     * @return
     */
    Optional<User> getByEmail(String email);
}

