package com.kamadhenu.signalsusermanagement.controller.general;

import com.kamadhenu.signalsusermanagement.controller.AbstractController;
import com.kamadhenu.signalsusermanagement.model.db.general.Feature;
import com.kamadhenu.signalsusermanagement.service.db.general.FeatureService;
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
 * <h1>feature controller</h1>
 * <p>
 * This feature controller class provides the CRUD actions for features
 *
 * @author Kamadhenu
 */
@Controller
@RequestMapping("/admin/feature")
public class FeatureController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeatureController.class);

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
        LOGGER.info("Feature index action called");
        ModelAndView modelAndView = new ModelAndView();
        List<Feature> featureList = featureService.getAll();
        modelAndView.addObject("models", featureList);
        modelAndView.setViewName("admin/general/feature/index");
        LOGGER.info("Feature index action returned {} models", featureList.size());
        return modelAndView;
    }

    /**
     * Edit action
     *
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/edit", "/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") Optional<Integer> id) {
        LOGGER.info("Feature edit action called");
        ModelAndView modelAndView = new ModelAndView();
        Feature model = new Feature();
        if (id.isPresent()) {
            Integer identifier = id.map(Integer::intValue).get();
            LOGGER.info("Feature edit action called on existing model with id {}", identifier);
            Optional<Feature> feature = featureService.get(identifier);
            if (feature.isPresent()) {
                model = feature.get();
            }
        }
        modelAndView.addObject("model", model);
        modelAndView.setViewName("admin/general/feature/edit");
        LOGGER.info("Feature edit action returned {} model", model);
        return modelAndView;
    }

    /**
     * Save action
     *
     * @return the model and view
     */
    @PostMapping("/save")
    public ModelAndView save(
            @Valid @ModelAttribute("model") Feature feature,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        LOGGER.info("Feature save action called");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/general/feature/edit");
        if (!result.hasErrors()) {
            try {
                featureService.edit(feature);
                addFlashMessage(redirectAttributes, true);
                LOGGER.info("Feature save action successful");
                modelAndView.setViewName("redirect:/admin/feature/index");
            } catch (DataIntegrityViolationException ex) {
                addFlashMessage(redirectAttributes, false, "Name already exist.");
                LOGGER.error(ex.getLocalizedMessage());
                LOGGER.debug(helperService.stackTraceToString(ex));
                modelAndView.setViewName("redirect:/admin/feature/index");
            } catch (Exception e) {
                addFlashMessage(redirectAttributes, false);
                LOGGER.error(e.getLocalizedMessage());
                LOGGER.debug(helperService.stackTraceToString(e));
            }
        } else {
            LOGGER.error("Feature save action had errors {}", result.getAllErrors());
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
        LOGGER.info("Feature delete action called on id {}", id);
        ModelAndView modelAndView = new ModelAndView();
        try {
            featureService.delete(id);
            LOGGER.info("Feature delete action successful");
            addFlashMessage(redirectAttributes, true);
        } catch (Exception e) {
            addFlashMessage(redirectAttributes, false);
            LOGGER.error(e.getLocalizedMessage());
            LOGGER.debug(helperService.stackTraceToString(e));
        }

        modelAndView.setViewName("redirect:/admin/feature/index");
        return modelAndView;
    }

}
