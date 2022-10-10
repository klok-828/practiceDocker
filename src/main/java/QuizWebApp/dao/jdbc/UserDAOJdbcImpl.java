package QuizWebApp.dao.jdbc;

import QuizWebApp.dao.UserDAO;
import QuizWebApp.domain.User;
import QuizWebApp.domain.UserPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOJdbcImpl implements UserDAO {
    JdbcTemplate jdbcTemplate;
    UserRowMapper rowMapper;

    UserPermissionRowMapper userPermissionRowMapper;

    @Autowired
    public UserDAOJdbcImpl(JdbcTemplate jdbcTemplate, UserRowMapper rowMapper, UserPermissionRowMapper userPermissionRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.userPermissionRowMapper = userPermissionRowMapper;
    }

    public void updateUserStatusById(int id) {
        String query = "UPDATE user SET is_active = NOT is_active WHERE user_id = ?";
        jdbcTemplate.update(query, id);
    }
    public void createNewUser(User user) {
        String query = "INSERT INTO user (firstName, lastName, email, password, phone, address, is_active, is_admin) values (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getPhone(), user.getAddress(), user.isActive(), user.isAdmin());
    }

    @Override
    public void deleteUserById(int id) {
        String query = "DELETE FROM user WHERE user_id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public Optional<User> loadUserByEmail(String email) {
        List<User> users = getAllUsers();

        return users.stream().filter(user -> email.equals(user.getEmail())).findAny();
    }

    @Override
    public List<String> getPermissionsByUserId(int id) {
        String query = "select name from permission where id IN (select permissionId from userpermission where userId = ?)";

        List<String> userPermissions = jdbcTemplate.queryForList(query, String.class, id);

        return userPermissions;
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM user";

        List<User> users = jdbcTemplate.query(query, rowMapper);

        return users;
    }

}

@Component
class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setPhone(rs.getString("phone"));
        user.setAddress(rs.getString("address"));
        user.setActive(rs.getBoolean("is_active"));
        user.setAdmin(rs.getBoolean("is_admin"));

        return user;
    }
}

@Component
class UserPermissionRowMapper implements RowMapper<UserPermission> {

    @Override
    public UserPermission mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserPermission userPermission = new UserPermission();

        userPermission.setId(rs.getInt("id"));
        userPermission.setUserId(rs.getInt("userId"));
        userPermission.setPermissionId(rs.getInt("permissionId"));

        return userPermission;
    }
}
