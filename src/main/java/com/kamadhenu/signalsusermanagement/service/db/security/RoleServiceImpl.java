package com.kamadhenu.signalsusermanagement.service.db.security;

import com.kamadhenu.signalsusermanagement.model.db.security.Role;
import com.kamadhenu.signalsusermanagement.repository.security.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Impl class for table role.
 *
 * @copyright Kamadhenu
 */

@Service
public class RoleServiceImpl implements RoleService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Get role
     *
     * @param id
     * @return
     */
    @Override
    public Optional<Role> get(Integer id) {
        return roleRepository.findById(id);
    }

    /**
     * Edit role
     *
     * @param role
     * @return
     */
    @Override
    public Role edit(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Delete role
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }

    /**
     * Get all role basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public List<Role> getAll(Integer pageNumber, Integer pageSize) {
        return roleRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    /**
     * Get all role
     *
     * @return
     */
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    /**
     * Get role count
     *
     * @return
     */
    public Long count() {
        return roleRepository.count();
    }
}
