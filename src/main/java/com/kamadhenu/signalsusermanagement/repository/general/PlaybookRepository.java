package com.kamadhenu.signalsusermanagement.repository.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Playbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Playbook repository class
 */
@Repository
public interface PlaybookRepository extends JpaRepository<Playbook, Integer> {

}
