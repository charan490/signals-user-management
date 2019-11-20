package com.kamadhenu.signalsusermanagement.service.api;

import com.kamadhenu.signalsusermanagement.exception.EntityNotFoundException;
import com.kamadhenu.signalsusermanagement.model.api.request.ApiUser;
import com.kamadhenu.signalsusermanagement.model.api.response.ApiUserResponse;
import com.kamadhenu.signalsusermanagement.model.api.response.GroupsPlaybookFeatureResponse;
import com.kamadhenu.signalsusermanagement.model.api.response.PlaybookFeatureResponse;
import com.kamadhenu.signalsusermanagement.model.api.response.UserGroupsResponse;
import com.kamadhenu.signalsusermanagement.model.db.general.GroupsPlaybookFeature;
import com.kamadhenu.signalsusermanagement.model.db.general.PlaybookFeature;
import com.kamadhenu.signalsusermanagement.model.db.security.User;
import com.kamadhenu.signalsusermanagement.model.db.security.UserGroups;
import com.kamadhenu.signalsusermanagement.service.db.general.GroupsPlaybookFeatureService;
import com.kamadhenu.signalsusermanagement.service.db.general.PlaybookFeatureService;
import com.kamadhenu.signalsusermanagement.service.db.security.UserGroupsService;
import com.kamadhenu.signalsusermanagement.service.db.security.UserService;
import com.kamadhenu.signalsusermanagement.service.domain.common.HelperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <h1>Api Service</h1>
 * This api service class will contain api related handling
 *
 * @author Kamadhenu
 */
@Service("apiService")
public class ApiService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserGroupsService userGroupsService;

    @Autowired
    private GroupsPlaybookFeatureService groupsPlaybookFeatureService;

    @Autowired
    private PlaybookFeatureService playbookFeatureService;

    @Autowired
    private HelperService helperService;

    /**
     * Get User Details
     * @param apiUser
     * @return
     */
    public ResponseEntity getUserDetails(ApiUser apiUser) throws EntityNotFoundException {
        HttpStatus httpStatus = HttpStatus.OK;
        boolean status = false;
        ApiUserResponse apiUserResponse = new ApiUserResponse();
        List<UserGroupsResponse> userGroupsResponses = new ArrayList<>();
        List<GroupsPlaybookFeatureResponse> groupsPlaybookFeatureResponses = new ArrayList<>();
        List<PlaybookFeatureResponse> playbookFeatureResponses = new ArrayList<>();
        Optional<User> userOptional = userService.getByEmail(apiUser.getEmail());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(user.getPassword().equalsIgnoreCase(helperService.getMD5(apiUser.getPassword()))){
                apiUserResponse.setName(user.getName());
                apiUserResponse.setEmail(user.getEmail());
                apiUserResponse.setExpirationDate(user.getExpirationDate());
                apiUserResponse.setRole(user.getRole().getName());
                apiUserResponse.setUserType(user.getUserType().getName());
                apiUserResponse.setCompany(user.getCompany().getName());
                List<UserGroups> userGroupsList = userGroupsService.getByUser(user);
                for(UserGroups userGroups:userGroupsList){
                    UserGroupsResponse userGroupsResponse = new UserGroupsResponse();
                    GroupsPlaybookFeatureResponse groupsPlaybookFeatureResponse = new GroupsPlaybookFeatureResponse();
                    LOGGER.info("user group found {}",userGroups.toString());
                    groupsPlaybookFeatureResponse.setGroup(userGroups.getGroups().getName());
                    List<GroupsPlaybookFeature> groupsPlaybookFeatures = groupsPlaybookFeatureService.getByGroups(userGroups.getGroups());
                    if(!groupsPlaybookFeatures.isEmpty()){
                        for(GroupsPlaybookFeature groupsPlaybookFeature:groupsPlaybookFeatures){
                            LOGGER.info("user group playbook feature found {}",groupsPlaybookFeature.toString());
                            Optional<PlaybookFeature> playbookFeatureOptional = playbookFeatureService.get(groupsPlaybookFeature.getPlaybookFeature());
                            if(playbookFeatureOptional.isPresent()){
                                PlaybookFeature playbookFeature = playbookFeatureOptional.get();
                                LOGGER.info("user group playbook feature detail found {}",playbookFeature.toString());
                                PlaybookFeatureResponse playbookFeatureResponse = new PlaybookFeatureResponse();
                                playbookFeatureResponse.setPlaybook(playbookFeature.getPlaybook().getName());
                                playbookFeatureResponse.setFeature(playbookFeature.getFeature().getName());
                                playbookFeatureResponses.add(playbookFeatureResponse);
                            }
                        }
                        groupsPlaybookFeatureResponse.setPlaybookFeatureResponses(playbookFeatureResponses);
                    }
                    groupsPlaybookFeatureResponses.add(groupsPlaybookFeatureResponse);
                    userGroupsResponse.setGroupsPlaybookFeatureResponses(groupsPlaybookFeatureResponses);
                    userGroupsResponses.add(userGroupsResponse);
                }
                apiUserResponse.setUserGroupsResponse(userGroupsResponses);
            }else {
                throw new EntityNotFoundException("Authentication failed");
            }
        }else {
            throw new EntityNotFoundException("Authentication failed");
        }
        return new ResponseEntity(apiUserResponse, httpStatus);
    }

}
