package com.kamadhenu.signalsusermanagement.config;


import com.kamadhenu.signalsusermanagement.repository.security.UserRepository;
import com.kamadhenu.signalsusermanagement.service.domain.common.HelperService;
import com.kamadhenu.signalsusermanagement.service.domain.user.CustomUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>Custom Security Configuration</h1>
 * <p>
 * This custom class configures the security system for spring boot
 *
 * @author Kamadhenu
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    private static final String LOGIN_URL = "/login";

    private static final String LOGOUT_URL = "/logout";

    private static final String LOGIN_SUCCESS_URL = "/admin/home";

    private static final String LOGIN_ERROR_URL = "/login?error=true";

    private static final String LOGOUT_SUCCESS_URL = "/";

    private static final String USERNAME_PARAMETER = "email";

    private static final String PASSWORD_PARAMETER = "password";

    private static final String HOME_URL = "/admin/home/**";

    private static final String API_URL = "/api/**";

    private final List<String> ADMIN_URLS = new ArrayList<>(
            Arrays.asList(
                    "/admin/security/user/**",
                    "/admin/home/**",
                    "/admin/general/**"
            )
    );

    private final List<String> IGNORE_URLS = new ArrayList<>(
            Arrays.asList(
                    "/resources/**",
                    "/static/**",
                    "/custom/**",
                    "/slim/**",
                    "/css/**",
                    "/js/**",
                    "/img/**",
                    "/xls/**",
                    "/lib/**"
            )
    );

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private HelperService helperService;

    /**
     * Configure security via custom user service
     *
     * @param auth the authentication manager
     * @throws Exception the exception that is thrown
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        LOGGER.debug("Configuring custom user details service");
        auth.userDetailsService(customUserService).passwordEncoder(getPasswordEncoder());
    }

    /**
     * Configure http security for various routes basis roles
     *
     * @param http the  http security manager
     * @throws Exception the exception that is thrown
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.debug("Configuring security");
        ///TODO Add logic to allow roles to be defined as constants
        http.authorizeRequests()
                .antMatchers(ADMIN_URLS.toArray(new String[ADMIN_URLS.size()])).hasRole("ADMIN")
                .antMatchers(LOGOUT_SUCCESS_URL, LOGIN_URL, HOME_URL, API_URL).permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable().formLogin()
                .loginPage(LOGIN_URL)
                .failureUrl(LOGIN_ERROR_URL)
                .defaultSuccessUrl(LOGIN_SUCCESS_URL)
                .successHandler(customAuthenticationSuccessHandler())
                .usernameParameter(USERNAME_PARAMETER)
                .passwordParameter(PASSWORD_PARAMETER)
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
                .logoutSuccessUrl(LOGOUT_SUCCESS_URL)
                .deleteCookies("JSESSIONID")
                .and().exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler());
    }

    /**
     * Configure ignores for allowing access without security session
     *
     * @param web the web security manager
     * @throws Exception the exception that is thrown
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        LOGGER.debug("Configuring matchers for security");
        web.ignoring().antMatchers(IGNORE_URLS.toArray(new String[IGNORE_URLS.size()]));
    }

    /**
     * Custom password encoder
     *
     * @return the password encoder
     */
    private PasswordEncoder getPasswordEncoder() {
        LOGGER.debug("Configuring password encoder");
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                String encodedPassword = helperService.getMD5(charSequence.toString());
                return encodedPassword.equals(s);
            }
        };
    }

    /**
     * Login success handler bean
     *
     * @return the custom authentication handler
     * @throws Exception the exception that is thrown
     */
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() throws Exception {
        LOGGER.debug("Configuring custom authentication handler");
        return new CustomAuthenticationSuccessHandler();
    }

    /**
     * Custom access denied handler bean
     *
     * @return the custom access denied handler bean
     * @throws Exception the exception that is thrown
     */
    public AccessDeniedHandler customAccessDeniedHandler() throws Exception {
        LOGGER.debug("Configuring custom access denied handler");
        return new CustomAccessDeniedHandler();
    }

}
