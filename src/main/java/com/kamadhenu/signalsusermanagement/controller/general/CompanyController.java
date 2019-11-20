package com.kamadhenu.signalsusermanagement.controller.general;

import com.kamadhenu.signalsusermanagement.controller.AbstractController;
import com.kamadhenu.signalsusermanagement.model.db.general.Company;
import com.kamadhenu.signalsusermanagement.service.db.general.CompanyService;
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
 * <h1>company controller</h1>
 * <p>
 * This company controller class provides the CRUD actions for companies
 *
 * @author Kamadhenu
 */
@Controller
@RequestMapping("/admin/company")
public class CompanyController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private HelperService helperService;

    /**
     * Index action
     *
     * @return the model and view
     */

    @GetMapping("/index")
    public ModelAndView index() {
        LOGGER.info("Company index action called");
        ModelAndView modelAndView = new ModelAndView();
        List<Company> companyList = companyService.getAll();
        modelAndView.addObject("models", companyList);
        modelAndView.setViewName("admin/general/company/index");
        LOGGER.info("Company index action returned {} models", companyList.size());
        return modelAndView;
    }

    /**
     * Edit action
     *
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/edit", "/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") Optional<Integer> id) {
        LOGGER.info("Company edit action called");
        ModelAndView modelAndView = new ModelAndView();
        Company model = new Company();
        if (id.isPresent()) {
            Integer identifier = id.map(Integer::intValue).get();
            LOGGER.info("Company edit action called on existing model with id {}", identifier);
            Optional<Company> company = companyService.get(identifier);
            if (company.isPresent()) {
                model = company.get();
            }
        }
        modelAndView.addObject("model", model);
        modelAndView.setViewName("admin/general/company/edit");
        LOGGER.info("Company edit action returned {} model", model);
        return modelAndView;
    }

    /**
     * Save action
     *
     * @return the model and view
     */
    @PostMapping("/save")
    public ModelAndView save(
            @Valid @ModelAttribute("model") Company company,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        LOGGER.info("Company save action called");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/general/company/edit");
        if (!result.hasErrors()) {
            try {
                companyService.edit(company);
                addFlashMessage(redirectAttributes, true);
                LOGGER.info("Company save action successful");
                modelAndView.setViewName("redirect:/admin/company/index");
            } catch (DataIntegrityViolationException ex) {
                addFlashMessage(redirectAttributes, false, "Name already exist.");
                LOGGER.error(ex.getLocalizedMessage());
                LOGGER.debug(helperService.stackTraceToString(ex));
                modelAndView.setViewName("redirect:/admin/company/index");
            } catch (Exception e) {
                addFlashMessage(redirectAttributes, false);
                LOGGER.error(e.getLocalizedMessage());
                LOGGER.debug(helperService.stackTraceToString(e));
            }
        } else {
            LOGGER.error("Company save action had errors {}", result.getAllErrors());
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
        LOGGER.info("Company delete action called on id {}", id);
        ModelAndView modelAndView = new ModelAndView();
        try {
            companyService.delete(id);
            LOGGER.info("Company delete action successful");
            addFlashMessage(redirectAttributes, true);
        } catch (Exception e) {
            addFlashMessage(redirectAttributes, false);
            LOGGER.error(e.getLocalizedMessage());
            LOGGER.debug(helperService.stackTraceToString(e));
        }

        modelAndView.setViewName("redirect:/admin/company/index");
        return modelAndView;
    }

}
