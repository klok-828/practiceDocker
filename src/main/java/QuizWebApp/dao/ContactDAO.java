package QuizWebApp.dao;

import QuizWebApp.domain.Contact;

import java.util.List;

public interface ContactDAO {
    void createNewContact(Contact contact);

    List<Contact> getAllContact();
}
