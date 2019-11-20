package com.kamadhenu.signalsusermanagement.controller;

import com.kamadhenu.signalsusermanagement.service.domain.common.ConstantService;
import com.kamadhenu.signalsusermanagement.service.domain.common.HelperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <h1>Abstract controller</h1>
 * <p>
 * This abstract controller class provides the base data needed for all other controllers
 *
 * @author Kamadhenu
 */
public abstract class AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

    @Autowired
    private ConstantService constantService;

    @Autowired
    private HelperService helperService;

    /**
     * Get model name split basis camel case
     *
     * @return the model name
     */
    private String getModelName() {
        LOGGER.debug("Getting model name");
        return helperService.splitCamelCase(this.getClass().getSimpleName().replaceAll("Controller", ""), "-")
                .toLowerCase();
    }

    /**
     * Get friendly model name split basis camel case
     *
     * @return the model name
     */
    private String getFriendlyModelName() {
        LOGGER.debug("Getting model name");
        return helperService.splitCamelCase(this.getClass().getSimpleName().replaceAll("Controller", ""), " ")
                .toLowerCase();
    }

    /**
     * Get controller name
     *
     * @return the controller name
     */
    @ModelAttribute("controller")
    public String getController() {
        LOGGER.debug("Getting controller name");
        return this.getModelName();
    }

    /**
     * Get friendly controller name
     *
     * @return the controller name
     */
    @ModelAttribute("friendlyController")
    public String getFriendlyController() {
        LOGGER.debug("Getting friendly controller name");
        return this.getFriendlyModelName();
    }

    /**
     * Get user id
     *
     * @return the logged in users id
     */
    @ModelAttribute("userId")
    public Integer getUserId() {
        LOGGER.debug("Getting user id");
        return helperService.getUserId();
    }

    /**
     * Get user first name
     *
     * @return the logged in users first name
     */
    @ModelAttribute("userFirstName")
    public String getUserFirstName() {
        LOGGER.debug("Getting user first name");
        return helperService.getUserName();
    }

    /**
     * Get user login time
     *
     * @return the logged in users login time
     */
    @ModelAttribute("loginTime")
    public String getLoginTime() {
        return helperService.getLoginTime();
    }

    /**
     * Get session time
     *
     * @return the logged in users session time
     */
    @ModelAttribute("sessionTime")
    public String getSessionTime() {
        return helperService.getSessionTime();
    }

    /**
     * Add redirect attributes to the current session
     *
     * @param redirectAttributes the redirect atributes object from the current controller action
     * @param success            if redirect attribute needs to be for a success or failure action
     */
    public void addFlashMessage(final RedirectAttributes redirectAttributes, Boolean success) {
        redirectAttributes.addFlashAttribute("success", success);
        redirectAttributes.addFlashAttribute("msg", success ? "Action performed successfully" : "Action failed");
    }

    /**
     * Add redirect attributes to the current session
     *
     * @param redirectAttributes the redirect atributes object from the current controller action
     * @param success            if redirect attribute needs to be for a success or failure action
     */
    public void addFlashMessage(final RedirectAttributes redirectAttributes, Boolean success, String message) {
        redirectAttributes.addFlashAttribute("success", success);
        redirectAttributes.addFlashAttribute(
                "msg",
                success ? "Action performed successfully " + message : "Action failed :" + message
        );
    }
}
