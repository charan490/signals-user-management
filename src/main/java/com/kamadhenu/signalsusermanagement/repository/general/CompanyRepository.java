package com.kamadhenu.signalsusermanagement.repository.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Company repository class
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
