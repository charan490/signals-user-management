package com.kamadhenu.signalsusermanagement.controller.api;

import com.kamadhenu.signalsusermanagement.exception.EntityNotFoundException;
import com.kamadhenu.signalsusermanagement.model.api.request.ApiUser;
import com.kamadhenu.signalsusermanagement.service.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <h1>Api Controller</h1>
 * This rest api controller class handles all json api request
 *
 * @author Kamadhenu
 *
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @PostMapping(path = "/login-api", consumes = "application/json", produces = "application/json")
    public ResponseEntity auth(@RequestBody ApiUser apiUser) throws EntityNotFoundException {
        return apiService.getUserDetails(apiUser);
    }

}
