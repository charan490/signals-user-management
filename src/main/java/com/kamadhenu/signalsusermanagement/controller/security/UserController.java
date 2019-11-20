package com.kamadhenu.signalsusermanagement.controller.security;

import com.kamadhenu.signalsusermanagement.controller.AbstractController;
import com.kamadhenu.signalsusermanagement.form.UserUploadForm;
import com.kamadhenu.signalsusermanagement.model.db.general.Company;
import com.kamadhenu.signalsusermanagement.model.db.general.Groups;
import com.kamadhenu.signalsusermanagement.model.db.security.Role;
import com.kamadhenu.signalsusermanagement.model.db.security.User;
import com.kamadhenu.signalsusermanagement.model.db.security.UserType;
import com.kamadhenu.signalsusermanagement.model.domain.user.UserModel;
import com.kamadhenu.signalsusermanagement.service.db.general.CompanyService;
import com.kamadhenu.signalsusermanagement.service.db.general.GroupsService;
import com.kamadhenu.signalsusermanagement.service.db.security.RoleService;
import com.kamadhenu.signalsusermanagement.service.db.security.UserService;
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
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


/**
 * <h1>user controller</h1>
 * <p>
 * This user controller class provides the CRUD actions for users
 *
 * @author Kamadhenu
 */
@Controller
@RequestMapping("/admin/user")
public class UserController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserTypeService userTypeService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private GroupsService groupsService;

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
        LOGGER.info("User index action called");
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = userService.getAll();
        modelAndView.addObject("models", userList);
        modelAndView.setViewName("admin/security/user/index");
        LOGGER.info("User index action returned {} models", userList.size());
        return modelAndView;
    }

    /**
     * Edit action
     *
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/edit", "/edit/{id}"})
    public ModelAndView edit(@PathVariable("id") Optional<Integer> id) {
        LOGGER.info("User edit action called");
        ModelAndView modelAndView = new ModelAndView();
        User model = new User();
        if (id.isPresent()) {
            Integer identifier = id.map(Integer::intValue).get();
            LOGGER.info("User edit action called on existing model with id {}", identifier);
            Optional<User> user = userService.get(identifier);
            if (user.isPresent()) {
                model = user.get();
            }
        }
        List<Role> roleList = roleService.getAll();
        List<UserType> userTypeList = userTypeService.getAll();
        List<Company> companyList = companyService.getAll();
        List<Groups> groupsList = groupsService.getAll();
        modelAndView.addObject("userTypes", userTypeList);
        modelAndView.addObject("roles", roleList);
        modelAndView.addObject("companies", companyList);
        modelAndView.addObject("groups", groupsList);
        modelAndView.addObject("model", model);
        modelAndView.setViewName("admin/security/user/edit");
        LOGGER.info("User edit action returned {} model", model);
        return modelAndView;
    }

    /**
     * Save action
     *
     * @return the model and view
     */
    @PostMapping("/save")
    public ModelAndView save(
            @Valid @ModelAttribute("model") User user,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        LOGGER.info("User save action called");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/security/user/edit");
        if (!result.hasErrors()) {
            try {
                userService.edit(user);
                addFlashMessage(redirectAttributes, true);
                LOGGER.info("User save action successful");
                modelAndView.setViewName("redirect:/admin/user/index");
            } catch (DataIntegrityViolationException ex) {
                addFlashMessage(redirectAttributes, false, "Email already exist.");
                LOGGER.error(ex.getLocalizedMessage());
                LOGGER.debug(helperService.stackTraceToString(ex));
                modelAndView.setViewName("redirect:/admin/security/user/index");
            } catch (Exception e) {
                addFlashMessage(redirectAttributes, false);
                LOGGER.error(e.getLocalizedMessage());
                LOGGER.debug(helperService.stackTraceToString(e));
            }
        } else {
            LOGGER.error("User save action had errors {}", result.getAllErrors());
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
        LOGGER.info("User delete action called on id {}", id);
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.delete(id);
            LOGGER.info("User delete action successful");
            addFlashMessage(redirectAttributes, true);
        } catch (Exception e) {
            addFlashMessage(redirectAttributes, false);
            LOGGER.error(e.getLocalizedMessage());
            LOGGER.debug(helperService.stackTraceToString(e));
        }

        modelAndView.setViewName("redirect:/admin/user/index");
        return modelAndView;
    }

    /**
     * Upload action
     *
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/upload"})
    public ModelAndView upload() {
        LOGGER.info("User upload action called");
        ModelAndView modelAndView = new ModelAndView();
        UserUploadForm userUploadForm = new UserUploadForm();
        modelAndView.addObject("model", userUploadForm);
        modelAndView.setViewName("admin/security/user/upload");
        return modelAndView;
    }

    /**
     * Upload action
     *
     * @return the model and view
     */
    @PostMapping("/upload")
    public ModelAndView save(@Valid @ModelAttribute("model") UserUploadForm userUploadForm,
                             BindingResult result,
                             RedirectAttributes redirectAttributes
    ) {
        LOGGER.info("Email save action called");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/security/user/upload");
        if (!result.hasErrors()) {
            try {
               /* if(!helperService.checkFileFormat(userUploadForm.getFile())){
                    LOGGER.info("email testing edit action returned error ");
                    addFlashMessage(redirectAttributes, false, "Please upload csv file.");
                    modelAndView.setViewName("redirect:/admin/user/upload");
                    return modelAndView;
                }*/
            } catch (Exception e) {
                addFlashMessage(redirectAttributes, false);
                LOGGER.error(e.getLocalizedMessage());
                LOGGER.debug(helperService.stackTraceToString(e));
            }
        }
            return modelAndView;
    }

    /**
     * Download action
     * @param response
     * @return
     */

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        String csvFileName = "users.csv";
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);
        String[] header = { "Id","Name", "Email", "ExpirationDate", "Password", "Role", "UserType", "Company" };
        csvWriter.writeHeader(header);
        List<User> users = userService.getAll();
        if(!users.isEmpty()) {
            for(User user:users) {
                UserModel userModel = getCsvDownloadData(user);
                csvWriter.write(userModel, header);
            }
        }
        csvWriter.close();
    }

    private UserModel getCsvDownloadData(User user){
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setName(user.getName());
        userModel.setEmail(user.getEmail());
        userModel.setExpirationDate(user.getExpirationDate());
        userModel.setPassword(user.getPassword());
        userModel.setRole(user.getRole().getName());
        userModel.setUserType(user.getUserType().getName());
        userModel.setCompany(user.getCompany().getName());
        return userModel;
    }

}
