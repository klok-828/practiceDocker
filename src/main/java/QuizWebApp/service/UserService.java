package QuizWebApp.service;

import QuizWebApp.dao.UserDAO;
import QuizWebApp.domain.User;
import QuizWebApp.security.AuthUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService implements UserDetailsService {
    private UserDAO userJdbcDAO;
    private UserDAO userHibernateDAO;

    @Autowired
    public void setUserJdbcDAO(@Qualifier("userDAOJdbcImpl") UserDAO userJdbcDAO) {
        this.userJdbcDAO = userJdbcDAO;
    }

    @Autowired
    @Qualifier("userDAOHibernateImpl")
    public void setUserHibernateDAO(UserDAO userHibernateDAO) {
        this.userHibernateDAO = userHibernateDAO;
    }
    public void reverseUserStatusById(int id) { userJdbcDAO.updateUserStatusById(id); }

    public void deleteUserById(int id) { userJdbcDAO.deleteUserById(id); }
    @Transactional
    public List<User> getAllUsers() { return userHibernateDAO.getAllUsers(); }

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<List<User>> getAllUsersAsync() {
        List<User> userList = userHibernateDAO.getAllUsers();
        return CompletableFuture.completedFuture(userList);
    }
    @Transactional
    public void createNewUser(User user) { userJdbcDAO.createNewUser(user); }
    @Transactional
    public Optional<User> validateUser(String email, String password) {
        return userJdbcDAO.getAllUsers().stream()
                .filter(user -> user.getEmail().equals(email)
                                && user.getPassword().equals(password)
                                && user.isActive())
                .findAny();
    }

    @Transactional
    public List<String> getUserPermissionById(int id) {
        return userJdbcDAO.getPermissionsByUserId(id);
    }

    @Transactional
    public Optional<User> validateAdmin(String email, String password) {
        return userJdbcDAO.getAllUsers().stream()
                .filter(user -> user.getEmail().equals(email)
                        && user.getPassword().equals(password)
                        && user.isActive()
                        && user.isAdmin())
                .findAny();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userJdbcDAO.loadUserByEmail(email);

        if (!userOptional.isPresent()){
            throw new UsernameNotFoundException("Username does not exist");
        }

        User user = userOptional.get(); // database user

        return AuthUserDetail.builder() // spring security's userDetail
                .email(user.getEmail())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .authorities(getAuthoritiesFromUser(user))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }

    private List<GrantedAuthority> getAuthoritiesFromUser(User user){
        List<GrantedAuthority> userAuthorities = new ArrayList<>();

        for (String permission :  getUserPermissionById(user.getId())){
            userAuthorities.add(new SimpleGrantedAuthority(permission));
        }

        return userAuthorities;
    }
}
