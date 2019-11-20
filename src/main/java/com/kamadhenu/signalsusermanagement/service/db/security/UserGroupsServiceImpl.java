package com.kamadhenu.signalsusermanagement.service.db.security;

import com.kamadhenu.signalsusermanagement.model.db.security.User;
import com.kamadhenu.signalsusermanagement.model.db.security.UserGroups;
import com.kamadhenu.signalsusermanagement.repository.security.UserGroupsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Impl class for table user_groups.
 *
 * @copyright Kamadhenu
 */

@Service
public class UserGroupsServiceImpl implements UserGroupsService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserGroupsRepository userGroupRepository;

    /**
     * Get user_groups
     *
     * @param id
     * @return
     */
    @Override
    public Optional<UserGroups> get(Integer id) {
        return userGroupRepository.findById(id);
    }

    /**
     * Edit user_groups
     *
     * @param userGroups
     * @return
     */
    @Override
    public UserGroups edit(UserGroups userGroups) {
        return userGroupRepository.save(userGroups);
    }

    /**
     * Delete user_groups
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        userGroupRepository.deleteById(id);
    }

    /**
     * Get all user_groups basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public List<UserGroups> getAll(Integer pageNumber, Integer pageSize) {
        return userGroupRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    /**
     * Get all user_groups
     *
     * @return
     */
    @Override
    public List<UserGroups> getAll() {
        return userGroupRepository.findAll();
    }

    /**
     * Get user_groups count
     *
     * @return
     */
    public Long count() {
        return userGroupRepository.count();
    }

    /**
     * Get all user_groups basis user
     *
     * @param user
     * @return list of userGroups
     */
    public List<UserGroups> getByUser(User user){
        return userGroupRepository.findByUser(user);
    }
}
