package com.kamadhenu.signalsusermanagement.repository.security;

import com.kamadhenu.signalsusermanagement.model.db.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Role repository class
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
