package com.kamadhenu.signalsusermanagement.model.db.general;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Data Model class for table groups_playbook_feature.
 *
 * @author Suneel Pandey
 * @copyright Kamadhenu
 */
@Entity
@Data
@ToString
@EqualsAndHashCode
@Table(name = "groups_playbook_feature")
public class GroupsPlaybookFeature {

    /**
     * The id of GroupsPlaybookFeature
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * The group of GroupPlaybook
     */
    @OneToOne
    @JoinColumn(name = "groups")
    @NotNull
    private Groups groups;

    /**
     * The playbook of GroupsPlaybookFeature
     */
    @Column(name = "playbook_feature")
    @NotNull
    private Integer playbookFeature;

}
