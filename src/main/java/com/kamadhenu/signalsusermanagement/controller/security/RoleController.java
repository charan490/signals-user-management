package com.kamadhenu.signalsusermanagement.controller.security;

import com.kamadhenu.signalsusermanagement.controller.AbstractController;
import com.kamadhenu.signalsusermanagement.model.db.security.Role;
import com.kamadhenu.signalsusermanagement.service.db.security.RoleService;
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
 * <h1>role controller</h1>
 * <p>
 * This role controller class provides the CRUD actions for roles
 *
 * @author Kamadhenu
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private HelperService helperService;

    /**
     * Index action
     *
     * @return the model and view
     */

    @GetMapping("/index")
    public ModelAndView index() {
        LOGGER.info("Role index action called");
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.getAll();
        modelAndView.addObject("models", roleList);
        modelAndView.setViewName("admin/security/role/index");
        LOGGER.info("Role index action returned {} models", roleList.size());
        return modelAndView;
    }

    /**
     * Edit action
     *
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/edit", "/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") Optional<Integer> id) {
        LOGGER.info("Role edit action called");
        ModelAndView modelAndView = new ModelAndView();
        Role model = new Role();
        if (id.isPresent()) {
            Integer identifier = id.map(Integer::intValue).get();
            LOGGER.info("Role edit action called on existing model with id {}", identifier);
            Optional<Role> role = roleService.get(identifier);
            if (role.isPresent()) {
                model = role.get();
            }
        }
        modelAndView.addObject("model", model);
        modelAndView.setViewName("admin/security/role/edit");
        LOGGER.info("Role edit action returned {} model", model);
        return modelAndView;
    }

    /**
     * Save action
     *
     * @return the model and view
     */
    @PostMapping("/save")
    public ModelAndView save(
            @Valid @ModelAttribute("model") Role role,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        LOGGER.info("Role save action called");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/security/role/edit");
        if (!result.hasErrors()) {
            try {
                roleService.edit(role);
                addFlashMessage(redirectAttributes, true);
                LOGGER.info("Role save action successful");
                modelAndView.setViewName("redirect:/admin/role/index");
            } catch (DataIntegrityViolationException ex) {
                addFlashMessage(redirectAttributes, false, "Role already exist.");
                LOGGER.error(ex.getLocalizedMessage());
                LOGGER.debug(helperService.stackTraceToString(ex));
                modelAndView.setViewName("redirect:/admin/role/index");
            } catch (Exception e) {
                addFlashMessage(redirectAttributes, false);
                LOGGER.error(e.getLocalizedMessage());
                LOGGER.debug(helperService.stackTraceToString(e));
            }
        } else {
            LOGGER.error("Role save action had errors {}", result.getAllErrors());
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
        LOGGER.info("Role delete action called on id {}", id);
        ModelAndView modelAndView = new ModelAndView();
        try {
            roleService.delete(id);
            LOGGER.info("Role delete action successful");
            addFlashMessage(redirectAttributes, true);
        } catch (Exception e) {
            addFlashMessage(redirectAttributes, false);
            LOGGER.error(e.getLocalizedMessage());
            LOGGER.debug(helperService.stackTraceToString(e));
        }

        modelAndView.setViewName("redirect:/admin/role/index");
        return modelAndView;
    }

}
