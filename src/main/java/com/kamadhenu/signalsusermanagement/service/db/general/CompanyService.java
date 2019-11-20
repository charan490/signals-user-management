package com.kamadhenu.signalsusermanagement.service.db.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Company;

import java.util.List;
import java.util.Optional;

/**
 * Company interface
 */
public interface CompanyService {

    /**
     * Get company
     *
     * @param id
     * @return
     */
    Optional<Company> get(Integer id);

    /**
     * Edit company
     *
     * @param company
     * @return
     */
    Company edit(Company company);

    /**
     * Delete company
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * Get all company basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<Company> getAll(Integer pageNumber, Integer pageSize);

    /**
     * Get all company
     *
     * @return
     */
    List<Company> getAll();

    /**
     * Get company count
     *
     * @return
     */
    Long count();
}

