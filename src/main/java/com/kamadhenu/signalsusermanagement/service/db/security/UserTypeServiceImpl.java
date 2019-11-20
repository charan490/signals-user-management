package com.kamadhenu.signalsusermanagement.service.db.security;

import com.kamadhenu.signalsusermanagement.model.db.security.UserType;
import com.kamadhenu.signalsusermanagement.repository.security.UserTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Impl class for table user_type.
 *
 * @copyright Kamadhenu
 */

@Service
public class UserTypeServiceImpl implements UserTypeService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserTypeRepository userTypeRepository;

    /**
     * Get user_type
     *
     * @param id
     * @return
     */
    @Override
    public Optional<UserType> get(Integer id) {
        return userTypeRepository.findById(id);
    }

    /**
     * Edit user_type
     *
     * @param userType
     * @return
     */
    @Override
    public UserType edit(UserType userType) {
        return userTypeRepository.save(userType);
    }

    /**
     * Delete user_type
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        userTypeRepository.deleteById(id);
    }

    /**
     * Get all user_type basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public List<UserType> getAll(Integer pageNumber, Integer pageSize) {
        return userTypeRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    /**
     * Get all user_type
     *
     * @return
     */
    @Override
    public List<UserType> getAll() {
        return userTypeRepository.findAll();
    }

    /**
     * Get user_type count
     *
     * @return
     */
    public Long count() {
        return userTypeRepository.count();
    }
}
