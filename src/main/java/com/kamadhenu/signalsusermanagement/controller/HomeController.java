package com.kamadhenu.signalsusermanagement.controller;


import com.kamadhenu.signalsusermanagement.service.db.general.CompanyService;
import com.kamadhenu.signalsusermanagement.service.db.general.FeatureService;
import com.kamadhenu.signalsusermanagement.service.db.general.GroupsService;
import com.kamadhenu.signalsusermanagement.service.db.general.PlaybookService;
import com.kamadhenu.signalsusermanagement.service.db.security.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <h1>Home controller</h1>
 * <p>
 * This home controller class provides the entry point to a logged in user
 *
 * @author Kamadhenu
 */
@Controller
@RequestMapping("/admin")
public class HomeController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;

    @Autowired
    PlaybookService playbookService;

    @Autowired
    GroupsService groupsService;

    @Autowired
    FeatureService featureService;

    /**
     * Home action
     *
     * @return the model and view
     */
    @GetMapping("/home")
    public ModelAndView home() {
        LOGGER.info("Home action reached");
        ModelAndView modelAndView = new ModelAndView();
        Long companyCount = companyService.count();
        Long userCount = userService.count();
        Long groupsCount = groupsService.count();
        Long playbookCount = playbookService.count();
        LOGGER.debug(
                "Counts for home action are - company count {} and user count {} and groups count {} and playbook count {}",
                companyCount,
                userCount,
                groupsCount,
                playbookCount
        );
        modelAndView.addObject("companiesCount", companyCount);
        modelAndView.addObject("usersCount", userCount);
        modelAndView.addObject("groupsCount", groupsCount);
        modelAndView.addObject("playbooksCount", playbookCount);
        modelAndView.setViewName("admin/home/index");
        return modelAndView;
    }

}
