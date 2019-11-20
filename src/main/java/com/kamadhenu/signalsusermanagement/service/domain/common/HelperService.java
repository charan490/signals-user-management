package com.kamadhenu.signalsusermanagement.service.domain.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamadhenu.signalsusermanagement.model.db.security.User;
import com.kamadhenu.signalsusermanagement.model.domain.user.CustomUser;
import com.kamadhenu.signalsusermanagement.service.db.security.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Helper service
 */
@Service("helperService")
public class HelperService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelperService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ConstantService constantService;

    /**
     * Split string by camel case
     *
     * @param s
     * @param replacement
     * @return
     */
    public String splitCamelCase(String s, String replacement) {
        return s.replaceAll(
                String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                replacement
        );
    }

    /**
     * Get session time
     *
     * @return
     */
    public String getSessionTime() {
        String sessionTime = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date loginTime = simpleDateFormat.parse(getLoginTime());
            Date now = new Date();
            long duration = now.getTime() - loginTime.getTime();
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
            long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
            sessionTime = diffInHours + " hrs " + diffInMinutes + " mins";
        } catch (ParseException ex) {
            LOGGER.error("exception found in getting session time {}", ex.getMessage());
        }
        return sessionTime;
    }

    /**
     * Get user login time
     *
     * @return
     */
    public String getLoginTime() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession().getAttribute("LOGIN_TIME").toString();
    }

    /**
     * Get logged in db user
     *
     * @return
     */
    public User getLoggedInDbUser() {
        Optional<User> user = userService.get(getUserId());
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    /**
     * Is user logged in
     *
     * @return
     */
    public Boolean isLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !(auth instanceof AnonymousAuthenticationToken);
    }

    /**
     * Get logged in user
     *
     * @return
     */
    public CustomUser getLoggedInUser() {
        return (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * Get user id
     *
     * @return
     */
    public Integer getUserId() {
        return getLoggedInUser().getId();
    }

    /**
     * Get user first name
     *
     * @return
     */
    public String getUserName() {
        return getLoggedInUser().getId() != null ? getLoggedInUser()
                .getName() : null;
    }

    /**
     * Get md5 message
     *
     * @param message
     * @return
     */
    public String getMD5(String message) {
        String md5 = null;
        try {
            MessageDigest sha1 = MessageDigest.getInstance("MD5");
            sha1.reset();
            sha1.update(message.getBytes());
            byte[] digest = sha1.digest();
            StringBuilder password = new StringBuilder();
            for (byte b : digest) {
                password.append(String.format("%02x", b & 0xff));
            }
            md5 = password.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getLocalizedMessage());
            LOGGER.debug(stackTraceToString(e));
        }
        return md5;
    }

    /**
     * Convert stack trace to string
     *
     * @param e the exception
     * @return the stack trace in string format
     */
    public String stackTraceToString(Exception e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Convert object to map
     *
     * @param o
     * @return
     */
    public Map<String, Object> objectMap(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(o, Map.class);
    }

    /**
     * Convert date to string
     *
     * @param date
     * @param format
     * @return
     */
    public String dateToString(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String formattedDate = formatter.format(date);
        return formattedDate;
    }
}
