package QuizWebApp.dao.hibernate;

import QuizWebApp.dao.AbstractHibernateDAO;
import QuizWebApp.dao.ContactDAO;
import QuizWebApp.domain.Contact;
import QuizWebApp.domain.User;
import QuizWebApp.domain.hiberntae.ContactHibernate;
import QuizWebApp.domain.hiberntae.UserHibernate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("contactDAOHibernateImpl")
public class ContactDAOHibernateImpl extends AbstractHibernateDAO<ContactHibernate> implements ContactDAO {

    public ContactDAOHibernateImpl() {
        setClazz(ContactHibernate.class);
    }


    @Override
    public void createNewContact(Contact contact) {
        add((ContactHibernate) contact);
    }

    @Override
    public List<Contact> getAllContact() {
        List<ContactHibernate> list = this.getAll();
        return list.stream().map(contact -> (Contact) contact).collect(Collectors.toList());
    }
}
