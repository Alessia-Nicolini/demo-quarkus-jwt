package it.itsincom.webdevd.service;

import io.quarkus.panache.common.Sort;
import it.itsincom.webdevd.persistence.UserRepository;
import it.itsincom.webdevd.persistence.model.ApplicationUser;
import it.itsincom.webdevd.web.model.user.CreateUserRequest;
import it.itsincom.webdevd.web.model.user.ModifyUserRequest;
import it.itsincom.webdevd.web.model.user.UserResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        ApplicationUser user = new ApplicationUser(
                request.getUsername(),
                request.getPassword(),
                request.getRole(),
                request.getFirstName(),
                request.getLastName(),
                request.getAddress()
        );

        userRepository.persist(user);

        return toUserResponse(user);

    }

    public UserResponse authenticate(String username, String password) {
        ApplicationUser user = userRepository.authenticate(username, password);
        if (user != null) {
            return toUserResponse(user);
        }
        return null;
    }

    public List<UserResponse> findAll() {
        List<ApplicationUser> allUsers = userRepository.findAll(Sort.by("username"))
                .list();
        List<UserResponse> result = new ArrayList<>();
        for (ApplicationUser user : allUsers) {
            result.add(toUserResponse(user));
        }
        return result;
    }

    private static UserResponse toUserResponse(ApplicationUser user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getFirstname(),
                user.getLastname(),
                user.getAddress()
        );
    }

    public UserResponse getUserByUsername(String username) {
        return toUserResponse(userRepository.findByUsername(username));
    }

    @Transactional
    public UserResponse modifyUser(ModifyUserRequest request, Long id)
    {
        boolean modify = userRepository.updateUser(id, request.getFirstName(), request.getLastName(), request.getAddress());
        if (modify) {
            ApplicationUser user = userRepository.findById(id);
            UserResponse newUser = toUserResponse(user);
            return newUser;
        } else {
            return null;
        }
    }
}
