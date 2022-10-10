package QuizWebApp.dao.hibernate;

import QuizWebApp.dao.AbstractHibernateDAO;
import QuizWebApp.dao.UserDAO;
import QuizWebApp.domain.User;
import QuizWebApp.domain.hiberntae.QuestionHibernate;
import QuizWebApp.domain.hiberntae.UserHibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("userDAOHibernateImpl")
public class UserDAOHibernateImpl extends AbstractHibernateDAO<UserHibernate> implements UserDAO {

    public UserDAOHibernateImpl() {
        setClazz(UserHibernate.class);
    }
    @Override
    public List<User> getAllUsers() {
        List<UserHibernate> list = this.getAll();
        return list.stream().map(user -> (User) user).collect(Collectors.toList());
    }

    @Override
    public void updateUserStatusById(int id) {
        Session session;
        session = getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<UserHibernate> update = cb.createCriteriaUpdate(UserHibernate.class);
        Root root = update.from(UserHibernate.class);
        update.set("is_active", true);
        update.where(cb.equal(root.get("user_id"), id));
        

    }

    @Override
    public void createNewUser(User user) {
        add((UserHibernate) user);
    }

    @Override
    public void deleteUserById(int id) {

    }


    @Override
    public Optional<User> loadUserByEmail(String email) {
        List<User> users = getAllUsers();
        return users.stream().filter(user -> email.equals(user.getEmail())).findAny();
    }

    @Override
    public List<String> getPermissionsByUserId(int id) {
        return null;
    }
}
