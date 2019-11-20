package com.kamadhenu.signalsusermanagement.repository.security;

import com.kamadhenu.signalsusermanagement.model.db.security.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserType repository class
 */
@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

}
