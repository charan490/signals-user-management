package com.kamadhenu.signalsusermanagement.service.db.general;

import com.kamadhenu.signalsusermanagement.model.db.general.Playbook;
import com.kamadhenu.signalsusermanagement.repository.general.PlaybookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Impl class for table playbook.
 *
 * @copyright Kamadhenu
 */

@Service
public class PlaybookServiceImpl implements PlaybookService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PlaybookRepository playbookRepository;

    /**
     * Get playbook
     *
     * @param id
     * @return
     */
    @Override
    public Optional<Playbook> get(Integer id) {
        return playbookRepository.findById(id);
    }

    /**
     * Edit playbook
     *
     * @param playbook
     * @return
     */
    @Override
    public Playbook edit(Playbook playbook) {
        return playbookRepository.save(playbook);
    }

    /**
     * Delete playbook
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        playbookRepository.deleteById(id);
    }

    /**
     * Get all playbook basis page number and size
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public List<Playbook> getAll(Integer pageNumber, Integer pageSize) {
        return playbookRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    /**
     * Get all playbook
     *
     * @return
     */
    @Override
    public List<Playbook> getAll() {
        return playbookRepository.findAll();
    }

    /**
     * Get playbook count
     *
     * @return
     */
    public Long count() {
        return playbookRepository.count();
    }
}
