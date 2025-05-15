package it.itsincom.webdevd.persistence.model;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;

@Entity
@Table(name = "ApplicationUser")
@UserDefinition
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Username", unique = true, nullable = false)
    @Username
    private String username;

    @Column(name = "Password", nullable = false)
    @Password
    private String password;

    @Column(name = "Role", nullable = false)
    @Roles
    private String role;

    @Column(name = "Firstname", nullable = false)
    private String firstname;

    @Column(name = "Lastname", nullable = false)
    private String lastname;


    @Column(name = "Address", nullable = false)
    private String address;

    public ApplicationUser() {
    }

    public ApplicationUser(String username, String password, UserRole role, String firstname, String lastname, String address) {
        this.username = username;
        this.password = BcryptUtil.bcryptHash(password);
        this.role = role.name();
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
