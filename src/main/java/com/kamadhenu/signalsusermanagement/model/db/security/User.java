package com.kamadhenu.signalsusermanagement.model.db.security;

import com.kamadhenu.signalsusermanagement.model.db.general.Company;
import com.kamadhenu.signalsusermanagement.model.db.general.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Data Model class for table user.
 *
 * @author Suneel Pandey
 * @copyright Kamadhenu
 */
@Entity
@Data
@ToString
@EqualsAndHashCode
@Table(name = "user")
public class User {

    /**
     * The id of User
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * The name of User
     */
    @Column(name = "name")
    @NotNull
    private String name;

    /**
     * The email of User
     */
    @Column(name = "email")
    @NotNull
    private String email;

    /**
     * The expirationDate of User
     */
    @Column(name = "expiration_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date expirationDate;

    /**
     * The password of User
     */
    @Column(name = "password")
    @NotNull
    private String password;

    /**
     * The role of User
     */
    @OneToOne
    @JoinColumn(name = "role")
    @NotNull
    private Role role;

    /**
     * The userType of User
     */
    @OneToOne
    @JoinColumn(name = "user_type")
    @NotNull
    private UserType userType;

    /**
     * The company of User
     */
    @OneToOne
    @JoinColumn(name = "company")
    @NotNull
    private Company company;

    /**
     * The group mapping to user
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_groups", joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "groups", referencedColumnName = "id"))
    private Set<Groups> groups;

    /**
     * Return user
     */
    public User() {

    }

    /**
     * Set user
     *
     * @param user
     */
    public User(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.expirationDate = user.getExpirationDate();
    }

    /**
     * Get active users
     *
     * @return
     * @throws ParseException
     */
    public boolean getActive() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = sdf.parse(sdf.format(new Date()));
        return this.getExpirationDate().after(currentDate);
    }

}
