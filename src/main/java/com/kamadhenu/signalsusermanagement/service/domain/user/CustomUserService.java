package com.kamadhenu.signalsusermanagement.service.domain.user;

import com.kamadhenu.signalsusermanagement.model.db.security.User;
import com.kamadhenu.signalsusermanagement.model.domain.user.CustomUser;
import com.kamadhenu.signalsusermanagement.repository.security.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Custom user service
 */
@Service
public class CustomUserService implements UserDetailsService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    /**
     * Load user by username / email
     *
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public CustomUser loadUserByUsername(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            User userModel = user.get();
            LOGGER.info("user model {}", userModel);
            if (userModel.getUserType().getName().equalsIgnoreCase("Signals")) {
                LOGGER.info("signals user found");
                CustomUser customUser = user.map(CustomUser::new).get();
                LOGGER.info("signals user found {}", customUser.toString());
                return customUser;
            } else {
                throw new UsernameNotFoundException("Signals User not found");
            }
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
