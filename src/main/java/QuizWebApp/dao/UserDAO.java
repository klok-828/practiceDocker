package QuizWebApp.dao;

import QuizWebApp.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> getAllUsers();
    void updateUserStatusById(int id);
    void createNewUser(User user);
    void deleteUserById(int id);

    Optional<User> loadUserByEmail(String email);

    List<String> getPermissionsByUserId(int id);
}
