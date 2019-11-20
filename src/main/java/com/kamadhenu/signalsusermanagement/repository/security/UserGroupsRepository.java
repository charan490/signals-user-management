package com.kamadhenu.signalsusermanagement.repository.security;

import com.kamadhenu.signalsusermanagement.model.db.security.User;
import com.kamadhenu.signalsusermanagement.model.db.security.UserGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserGroup repository class
 */
@Repository
public interface UserGroupsRepository extends JpaRepository<UserGroups, Integer> {

    /**
     * Find user_groups by user
     *
     * @param user
     * @return list of userGroups
     */
    List<UserGroups> findByUser(User user);
}
