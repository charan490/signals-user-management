package com.kamadhenu.signalsusermanagement.form;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * User Upload Form
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserUploadForm {

    @NotNull
    private MultipartFile file;
}
