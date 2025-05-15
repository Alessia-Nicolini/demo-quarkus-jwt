package it.itsincom.webdevd.persistence;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import it.itsincom.webdevd.persistence.model.ApplicationUser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<ApplicationUser, Long> {
    public ApplicationUser authenticate(String username, String password) {
        ApplicationUser applicationUser = findByUsername(username);
        if (applicationUser != null) {
            boolean matches = BcryptUtil.matches(password, applicationUser.getPassword());
            if (matches) {
                return applicationUser;
            } else {
                return null;
            }
        }
        return null;
    }

    public ApplicationUser findByUsername(String username) {
        ApplicationUser applicationUser = find(
                "SELECT u from ApplicationUser u where " +
                "u.username = :username ",
                Parameters.with("username", username)
        ).firstResult();
        return applicationUser;
    }

    @Transactional
    public boolean updateUser(String username, String firstName, String lastName, String address) {
        int modify = update("UPDATE ApplicationUser u SET u.firstname = :firstname, u.lastname = :lastname, u.address = :address WHERE u.username = :username",
                Parameters.with("firstname", firstName)
                        .and("lastname", lastName)
                        .and("address", address)
                        .and("username", username));

        return modify > 0;
    }
}
