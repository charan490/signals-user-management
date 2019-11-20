package com.kamadhenu.signalsusermanagement.service.db.security;

import com.kamadhenu.signalsusermanagement.model.db.security.UserType;

import java.util.List;
import java.util.Optional;

/**
 * UserType interface
 */
public interface UserTypeService {

    /**
     * Get user_type
     *
     * @param id
     * @return
     */
    Optional<UserType> get(Integer id);

    /**
     * Edit user_type
     *
     * @param userType
     * @return
     */
    UserType edit(UserType userType);

    /**
     * Delete user_type
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * Get all user_type basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<UserType> getAll(Integer pageNumber, Integer pageSize);

    /**
     * Get all user_type
     *
     * @return
     */
    List<UserType> getAll();

    /**
     * Get user_type count
     *
     * @return
     */
    Long count();
}

