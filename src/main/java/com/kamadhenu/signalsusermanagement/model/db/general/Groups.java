package com.kamadhenu.signalsusermanagement.model.db.general;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Data Model class for table group.
 *
 * @author Suneel Pandey
 * @copyright Kamadhenu
 */
@Entity
@Data
@ToString
@EqualsAndHashCode
@Table(name = "groups")
public class Groups {

    /**
     * The id of Group
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * The name of Group
     */
    @Column(name = "name")
    @NotNull
    private String name;

}
