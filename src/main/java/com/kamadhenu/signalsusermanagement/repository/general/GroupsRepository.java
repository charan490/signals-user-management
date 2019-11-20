package com.kamadhenu.signalsusermanagement.repository.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Group repository class
 */
@Repository
public interface GroupsRepository extends JpaRepository<Groups, Integer> {

}
