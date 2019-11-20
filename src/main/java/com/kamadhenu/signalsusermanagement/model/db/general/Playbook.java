package com.kamadhenu.signalsusermanagement.model.db.general;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Set;

/**
 * Data Model class for table playbook.
 *
 * @author Suneel Pandey
 * @copyright Kamadhenu
 */
@Entity
@Data
@ToString
@EqualsAndHashCode
@Table(name = "playbook")
public class Playbook {

    /**
     * The id of Playbook
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * The name of Playbook
     */
    @Column(name = "name")
    @NotNull
    private String name;

    /**
     * The uuid of Playbook
     */
    @Column(name = "uuid")
    @NotNull
    private BigInteger uuid;

    /**
     * The feature mapping of Playbook
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "playbook_feature", joinColumns = @JoinColumn(name = "playbook", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "feature", referencedColumnName = "id"))
    private Set<Feature> feature;
}
