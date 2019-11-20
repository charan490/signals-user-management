package com.kamadhenu.signalsusermanagement.controller.general;

import com.kamadhenu.signalsusermanagement.controller.AbstractController;
import com.kamadhenu.signalsusermanagement.model.db.general.Feature;
import com.kamadhenu.signalsusermanagement.model.db.general.Playbook;
import com.kamadhenu.signalsusermanagement.service.db.general.FeatureService;
import com.kamadhenu.signalsusermanagement.service.db.general.PlaybookService;
import com.kamadhenu.signalsusermanagement.service.domain.common.HelperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


/**
 * <h1>playbook controller</h1>
 * <p>
 * This playbook controller class provides the CRUD actions for playbooks
 *
 * @author Kamadhenu
 */
@Controller
@RequestMapping("/admin/playbook")
public class PlaybookController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaybookController.class);

    @Autowired
    private PlaybookService playbookService;

    @Autowired
    private FeatureService featureService;

    @Autowired
    private HelperService helperService;

    /**
     * Index action
     *
     * @return the model and view
     */

    @GetMapping("/index")
    public ModelAndView index() {
        LOGGER.info("Playbook index action called");
        ModelAndView modelAndView = new ModelAndView();
        List<Playbook> playbookList = playbookService.getAll();
        modelAndView.addObject("models", playbookList);
        modelAndView.setViewName("admin/general/playbook/index");
        LOGGER.info("Playbook index action returned {} models", playbookList.size());
        return modelAndView;
    }

    /**
     * Edit action
     *
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/edit", "/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") Optional<Integer> id) {
        LOGGER.info("Playbook edit action called");
        ModelAndView modelAndView = new ModelAndView();
        Playbook model = new Playbook();
        if (id.isPresent()) {
            Integer identifier = id.map(Integer::intValue).get();
            LOGGER.info("Playbook edit action called on existing model with id {}", identifier);
            Optional<Playbook> playbook = playbookService.get(identifier);
            if (playbook.isPresent()) {
                model = playbook.get();
            }
        }
        List<Feature> featureList = featureService.getAll();
        modelAndView.addObject("features", featureList);
        modelAndView.addObject("model", model);
        modelAndView.setViewName("admin/general/playbook/edit");
        LOGGER.info("Playbook edit action returned {} model", model);
        return modelAndView;
    }

    /**
     * Save action
     *
     * @return the model and view
     */
    @PostMapping("/save")
    public ModelAndView save(
            @Valid @ModelAttribute("model") Playbook playbook,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        LOGGER.info("Playbook save action called");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/general/playbook/edit");
        if (!result.hasErrors()) {
            try {
                playbookService.edit(playbook);
                addFlashMessage(redirectAttributes, true);
                LOGGER.info("Playbook save action successful");
                modelAndView.setViewName("redirect:/admin/playbook/index");
            } catch (DataIntegrityViolationException ex) {
                addFlashMessage(redirectAttributes, false, "Name or uuid already exist.");
                LOGGER.error(ex.getLocalizedMessage());
                LOGGER.debug(helperService.stackTraceToString(ex));
                modelAndView.setViewName("redirect:/admin/playbook/index");
            } catch (Exception e) {
                addFlashMessage(redirectAttributes, false);
                LOGGER.error(e.getLocalizedMessage());
                LOGGER.debug(helperService.stackTraceToString(e));
            }
        } else {
            List<Feature> featureList = featureService.getAll();
            modelAndView.addObject("features", featureList);
            LOGGER.error("Playbook save action had errors {}", result.getAllErrors());
        }
        return modelAndView;
    }

    /**
     * Delete action
     *
     * @return the model and view
     */
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        LOGGER.info("Playbook delete action called on id {}", id);
        ModelAndView modelAndView = new ModelAndView();
        try {
            playbookService.delete(id);
            LOGGER.info("Playbook delete action successful");
            addFlashMessage(redirectAttributes, true);
        } catch (Exception e) {
            addFlashMessage(redirectAttributes, false);
            LOGGER.error(e.getLocalizedMessage());
            LOGGER.debug(helperService.stackTraceToString(e));
        }

        modelAndView.setViewName("redirect:/admin/playbook/index");
        return modelAndView;
    }

}
