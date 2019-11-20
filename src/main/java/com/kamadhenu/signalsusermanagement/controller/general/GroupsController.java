package com.kamadhenu.signalsusermanagement.controller.general;

import com.kamadhenu.signalsusermanagement.controller.AbstractController;
import com.kamadhenu.signalsusermanagement.form.GroupsPlaybookFeatureForm;
import com.kamadhenu.signalsusermanagement.model.db.general.Groups;
import com.kamadhenu.signalsusermanagement.model.db.general.GroupsPlaybookFeature;
import com.kamadhenu.signalsusermanagement.model.db.general.PlaybookFeature;
import com.kamadhenu.signalsusermanagement.service.db.general.GroupsPlaybookFeatureService;
import com.kamadhenu.signalsusermanagement.service.db.general.GroupsService;
import com.kamadhenu.signalsusermanagement.service.db.general.PlaybookFeatureService;
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
 * <h1>group controller</h1>
 * <p>
 * This group controller class provides the CRUD actions for groups
 *
 * @author Kamadhenu
 */
@Controller
@RequestMapping("/admin/groups")
public class GroupsController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupsController.class);

    @Autowired
    private GroupsService groupsService;

    @Autowired
    private PlaybookService playbookService;

    @Autowired
    private PlaybookFeatureService playbookFeatureService;

    @Autowired
    private GroupsPlaybookFeatureService groupsPlaybookFeatureService;

    @Autowired
    private HelperService helperService;

    /**
     * Index action
     *
     * @return the model and view
     */

    @GetMapping("/index")
    public ModelAndView index() {
        LOGGER.info("Groups index action called");
        ModelAndView modelAndView = new ModelAndView();
        List<Groups> groupList = groupsService.getAll();
        modelAndView.addObject("models", groupList);
        modelAndView.setViewName("admin/general/groups/index");
        LOGGER.info("Group index action returned {} models", groupList.size());
        return modelAndView;
    }

    /**
     * Edit action
     *
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/edit", "/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") Optional<Integer> id) {
        LOGGER.info("Group edit action called");
        ModelAndView modelAndView = new ModelAndView();
        GroupsPlaybookFeatureForm model = new GroupsPlaybookFeatureForm();
        Groups groups = new Groups();
        if (id.isPresent()) {
            Integer identifier = id.map(Integer::intValue).get();
            LOGGER.info("Group edit action called on existing model with id {}", identifier);
            Optional<Groups> group = groupsService.get(identifier);
            if (group.isPresent()) {
                groups = group.get();
                model.setPlaybookFeature(playbookFeatureService.getByGroups(groups));
            }
        }
        model.setGroups(groups);
        List<PlaybookFeature> playbookFeatureList = playbookFeatureService.getAll();
        modelAndView.addObject("model", model);
        modelAndView.addObject("playbookFeatures", playbookFeatureList);
        modelAndView.setViewName("admin/general/groups/edit");
        LOGGER.info("Group edit action returned {} model", model);
        return modelAndView;
    }

    /**
     * Save action
     *
     * @return the model and view
     */
    @PostMapping("/save")
    public ModelAndView save(
            @Valid @ModelAttribute("model") GroupsPlaybookFeatureForm groupsPlaybookFeatureForm,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        LOGGER.info("Group save action called");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/general/groups/edit");
        if (!result.hasErrors()) {
            try {
                Groups groups = groupsService.edit(groupsPlaybookFeatureForm.getGroups());
                List<GroupsPlaybookFeature> groupsPlaybookFeatures = groupsPlaybookFeatureService.getByGroups(groups);
                for (GroupsPlaybookFeature groupsPlaybookFeature : groupsPlaybookFeatures) {
                    groupsPlaybookFeatureService.delete(groupsPlaybookFeature.getId());
                }
                for (Integer playbookFeature : groupsPlaybookFeatureForm.getPlaybookFeature()) {
                    GroupsPlaybookFeature groupsPlaybookFeature = new GroupsPlaybookFeature();
                    groupsPlaybookFeature.setGroups(groups);
                    groupsPlaybookFeature.setPlaybookFeature(playbookFeature);
                    groupsPlaybookFeatureService.edit(groupsPlaybookFeature);
                }
                addFlashMessage(redirectAttributes, true);
                LOGGER.info("Group save action successful");
                modelAndView.setViewName("redirect:/admin/groups/index");
            } catch (DataIntegrityViolationException ex) {
                addFlashMessage(redirectAttributes, false, "Name already exist.");
                LOGGER.error(ex.getLocalizedMessage());
                LOGGER.debug(helperService.stackTraceToString(ex));
                modelAndView.setViewName("redirect:/admin/groups/index");
            } catch (Exception e) {
                addFlashMessage(redirectAttributes, false);
                LOGGER.error(e.getLocalizedMessage());
                LOGGER.debug(helperService.stackTraceToString(e));
            }
        } else {
            LOGGER.error("Group save action had errors {}", result.getAllErrors());
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
        LOGGER.info("Group delete action called on id {}", id);
        ModelAndView modelAndView = new ModelAndView();
        try {
            groupsService.delete(id);
            LOGGER.info("Group delete action successful");
            addFlashMessage(redirectAttributes, true);
        } catch (Exception e) {
            addFlashMessage(redirectAttributes, false);
            LOGGER.error(e.getLocalizedMessage());
            LOGGER.debug(helperService.stackTraceToString(e));
        }

        modelAndView.setViewName("redirect:/admin/groups/index");
        return modelAndView;
    }

}
