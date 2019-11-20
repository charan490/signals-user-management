package com.kamadhenu.signalsusermanagement.model.db.general;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Data Model class for table playbook_feature.
 *
 * @author Suneel Pandey
 * @copyright Kamadhenu
 */
@Entity
@Data
@ToString
@EqualsAndHashCode
@Table(name = "playbook_feature")
public class PlaybookFeature {

    /**
     * The id of PlaybookFeature
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * The playbook of PlaybookFeature
     */
    @OneToOne
    @JoinColumn(name = "playbook")
    @NotNull
    private Playbook playbook;

    /**
     * The feature of PlaybookFeature
     */
    @OneToOne
    @JoinColumn(name = "feature")
    @NotNull
    private Feature feature;


}
