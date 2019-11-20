package com.kamadhenu.signalsusermanagement.model.domain.user;

import com.kamadhenu.signalsusermanagement.model.db.security.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Custom user model
 */
public class CustomUser extends User implements UserDetails {

    /**
     * Create custom user
     *
     * @param user
     */
    public CustomUser(final User user) {
        super(user);
    }

    /**
     * Get granted authorities
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<String> roles = new HashSet<>();
        roles.add(super.getRole().getName());
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    /**
     * Get password
     *
     * @return
     */
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    /**
     * Get username
     *
     * @return
     */
    @Override
    public String getUsername() {
        return super.getName();
    }

    /**
     * Get if account is not expired
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        try {
            return super.getActive();
        } catch (Exception ex) {

        }
        return false;
    }

    /**
     * Get if account is not locked
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        try {
            return super.getActive();
        } catch (Exception ex) {

        }
        return false;
    }

    /**
     * Get if credentials are not expired
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        try {
            return super.getActive();
        } catch (Exception ex) {

        }
        return false;
    }

    /**
     * Get if account is enabled
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        try {
            return super.getActive();
        } catch (Exception ex) {

        }
        return false;
    }
}
