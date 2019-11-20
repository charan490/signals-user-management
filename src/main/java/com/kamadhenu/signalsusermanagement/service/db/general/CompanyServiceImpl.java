package com.kamadhenu.signalsusermanagement.service.db.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Company;
import com.kamadhenu.signalsusermanagement.repository.general.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Impl class for table company.
 *
 * @copyright Kamadhenu
 */

@Service
public class CompanyServiceImpl implements CompanyService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CompanyRepository companyRepository;

    /**
     * Get company
     *
     * @param id
     * @return
     */
    @Override
    public Optional<Company> get(Integer id) {
        return companyRepository.findById(id);
    }

    /**
     * Edit company
     *
     * @param company
     * @return
     */
    @Override
    public Company edit(Company company) {
        return companyRepository.save(company);
    }

    /**
     * Delete company
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        companyRepository.deleteById(id);
    }

    /**
     * Get all company basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public List<Company> getAll(Integer pageNumber, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    /**
     * Get all company
     *
     * @return
     */
    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    /**
     * Get company count
     *
     * @return
     */
    public Long count() {
        return companyRepository.count();
    }
}
