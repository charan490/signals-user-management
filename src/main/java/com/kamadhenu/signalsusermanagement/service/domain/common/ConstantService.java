package com.kamadhenu.signalsusermanagement.service.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Constant service
 */
@Service("constantService")
@Data
@ToString
@EqualsAndHashCode
public class ConstantService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConstantService.class);

    // User and Employee constants
    private final List<String> TITLES = new ArrayList<>(
            Arrays.asList(
                    "Mr.",
                    "Mrs.",
                    "Ms."
            )
    );

    private final List<String> ADMIN_ROLES = new ArrayList<>(
            Arrays.asList(
                    "ROLE_ADMIN"
            )
    );
}

