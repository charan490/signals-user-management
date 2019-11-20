package com.kamadhenu.signalsusermanagement.config;

import com.kamadhenu.signalsusermanagement.model.domain.user.CustomUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h1>Custom Authentication Success Handler</h1>
 * <p>
 * This custom class on authentication success updates user activity
 *
 * @author Kamadhenu
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    private static final Integer SESSION_TIMEOUT_IN_SECONDS = 60 * 60 * 24;

    private static final String LOGIN_SUCCESS_URL = "/admin/home";

    /**
     * On authentication success updates user activity and redirects to success url
     *
     * @param request        the http servlet request
     * @param response       the http servlet response
     * @param authentication the authentication
     * @throws IOException      the io exception that is thrown
     * @throws ServletException the servlet exception that is thrown
     */
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        LOGGER.info("authentication called");
        LOGGER.debug("authenticated user found {}", customUser.toString());
        request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT_IN_SECONDS);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        request.getSession().setAttribute("LOGIN_TIME", simpleDateFormat.format(new Date()));
        response.sendRedirect(request.getContextPath() + LOGIN_SUCCESS_URL);
    }
}
