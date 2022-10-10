package QuizWebApp.service;

import QuizWebApp.dao.ContactDAO;
import QuizWebApp.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ContactService {
    private ContactDAO contactJdbcDAO;
    private ContactDAO contactHibernateDAO;

    @Autowired
    @Qualifier("contactDAOJdbcImpl")
    public void setContactJdbcDAO(ContactDAO contactJdbcDAO) {
        this.contactJdbcDAO = contactJdbcDAO;
    }

    @Autowired
    @Qualifier("contactDAOHibernateImpl")
    public void setContactHibernateDAO(ContactDAO contactHibernateDAO) {
        this.contactHibernateDAO = contactHibernateDAO;
    }
    @Transactional
    public List<Contact> getAllContacts() { return contactHibernateDAO.getAllContact(); }


    @Transactional
    public void createNewContact(Contact contact) { contactHibernateDAO.createNewContact(contact); }
}
