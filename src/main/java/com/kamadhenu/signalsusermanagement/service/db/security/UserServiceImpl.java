package com.kamadhenu.signalsusermanagement.service.db.security;

import com.kamadhenu.signalsusermanagement.model.db.security.User;
import com.kamadhenu.signalsusermanagement.repository.security.UserRepository;
import com.kamadhenu.signalsusermanagement.service.domain.common.HelperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Impl class for table user.
 *
 * @copyright Kamadhenu
 */

@Service
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HelperService helperService;


    /**
     * Get user
     *
     * @param id
     * @return
     */
    @Override
    public Optional<User> get(Integer id) {
        return userRepository.findById(id);
    }

    /**
     * Edit user
     *
     * @param user
     * @return
     */
    @Override
    public User edit(User user) {
        String md5Password = helperService.getMD5(user.getPassword());
        if (user.getId() != null) {
            Optional<User> currentModel = userRepository.findById(user.getId());
            if (currentModel.isPresent()) {
                User currentUser = currentModel.get();
                if (!currentUser.getPassword().equalsIgnoreCase(user.getPassword())) {
                    user.setPassword(md5Password);
                } else {
                    user.setPassword(currentUser.getPassword());
                }
            }
        } else {
            user.setPassword(md5Password);
        }

        return userRepository.save(user);
    }

    /**
     * Delete user
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    /**
     * Get all user basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public List<User> getAll(Integer pageNumber, Integer pageSize) {
        return userRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    /**
     * Get all user
     *
     * @return
     */
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Get user count
     *
     * @return
     */
    public Long count() {
        return userRepository.count();
    }

    /**
     * Get user by email
     *
     * @param email
     * @return
     */
    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
