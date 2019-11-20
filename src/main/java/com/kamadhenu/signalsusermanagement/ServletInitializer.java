package com.kamadhenu.signalsusermanagement;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Servlet Initializer
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * Configure application
     *
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SignalsUserManagementApplication.class);
    }

}
