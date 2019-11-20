package com.kamadhenu.signalsusermanagement.model.db.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Data Model class for table role.
 *
 * @author Suneel Pandey
 * @copyright Kamadhenu
 */
@Entity
@Data
@ToString
@EqualsAndHashCode
@Table(name = "role")
public class Role {

    /**
     * The id of Role
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * The name of Role
     */
    @Column(name = "name")
    @NotNull
    private String name;


}
