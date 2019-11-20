package com.kamadhenu.signalsusermanagement.model.db.security;

import com.kamadhenu.signalsusermanagement.model.db.general.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Data Model class for table user_group.
 *
 * @author Suneel Pandey
 * @copyright Kamadhenu
 */
@Entity
@Data
@ToString
@EqualsAndHashCode
@Table(name = "user_groups")
public class UserGroups {

    /**
     * The id of UserGroup
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * The user of UserGroup
     */
    @OneToOne
    @JoinColumn(name = "user")
    @NotNull
    private User user;

    /**
     * The group of UserGroup
     */
    @OneToOne
    @JoinColumn(name = "groups")
    @NotNull
    private Groups groups;


}
