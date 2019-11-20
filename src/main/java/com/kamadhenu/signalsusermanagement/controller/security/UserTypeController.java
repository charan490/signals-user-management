package com.kamadhenu.signalsusermanagement.controller.security;

import com.kamadhenu.signalsusermanagement.controller.AbstractController;
import com.kamadhenu.signalsusermanagement.model.db.security.UserType;
import com.kamadhenu.signalsusermanagement.service.db.security.UserTypeService;
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
 * <h1>user_type controller</h1>
 * <p>
 * This user_type controller class provides the CRUD actions for user_types
 *
 * @author Kamadhenu
 */
@Controller
@RequestMapping("/admin/user-type")
public class UserTypeController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserTypeController.class);

    @Autowired
    private UserTypeService userTypeService;

    @Autowired
    private HelperService helperService;

    /**
     * Index action
     *
     * @return the model and view
     */

    @GetMapping("/index")
    public ModelAndView index() {
        LOGGER.info("UserType index action called");
        ModelAndView modelAndView = new ModelAndView();
        List<UserType> userTypeList = userTypeService.getAll();
        modelAndView.addObject("models", userTypeList);
        modelAndView.setViewName("admin/security/user-type/index");
        LOGGER.info("UserType index action returned {} models", userTypeList.size());
        return modelAndView;
    }

    /**
     * Edit action
     *
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/edit", "/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") Optional<Integer> id) {
        LOGGER.info("UserType edit action called");
        ModelAndView modelAndView = new ModelAndView();
        UserType model = new UserType();
        if (id.isPresent()) {
            Integer identifier = id.map(Integer::intValue).get();
            LOGGER.info("UserType edit action called on existing model with id {}", identifier);
            Optional<UserType> userType = userTypeService.get(identifier);
            if (userType.isPresent()) {
                model = userType.get();
            }
        }
        modelAndView.addObject("model", model);
        modelAndView.setViewName("admin/security/user-type/edit");
        LOGGER.info("UserType edit action returned {} model", model);
        return modelAndView;
    }

    /**
     * Save action
     *
     * @return the model and view
     */
    @PostMapping("/save")
    public ModelAndView save(
            @Valid @ModelAttribute("model") UserType userType,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        LOGGER.info("UserType save action called");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/security/user-type/edit");
        if (!result.hasErrors()) {
            try {
                userTypeService.edit(userType);
                addFlashMessage(redirectAttributes, true);
                LOGGER.info("UserType save action successful");
                modelAndView.setViewName("redirect:/admin/user-type/index");
            } catch (DataIntegrityViolationException ex) {
                addFlashMessage(redirectAttributes, false, "Name already exist.");
                LOGGER.error(ex.getLocalizedMessage());
                LOGGER.debug(helperService.stackTraceToString(ex));
                modelAndView.setViewName("redirect:/admin/security/user-type/index");
            } catch (Exception e) {
                addFlashMessage(redirectAttributes, false);
                LOGGER.error(e.getLocalizedMessage());
                LOGGER.debug(helperService.stackTraceToString(e));
            }
        } else {
            LOGGER.error("UserType save action had errors {}", result.getAllErrors());
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
        LOGGER.info("UserType delete action called on id {}", id);
        ModelAndView modelAndView = new ModelAndView();
        try {
            userTypeService.delete(id);
            LOGGER.info("UserType delete action successful");
            addFlashMessage(redirectAttributes, true);
        } catch (Exception e) {
            addFlashMessage(redirectAttributes, false);
            LOGGER.error(e.getLocalizedMessage());
            LOGGER.debug(helperService.stackTraceToString(e));
        }

        modelAndView.setViewName("redirect:/admin/user-type/index");
        return modelAndView;
    }

}
