package QuizWebApp.dao.jdbc;

import QuizWebApp.dao.ContactDAO;
import QuizWebApp.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("contactDAOJdbcImpl")
public class ContactDAOJdbcImpl implements ContactDAO {
    JdbcTemplate jdbcTemplate;
    ContactRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ContactDAOJdbcImpl(JdbcTemplate jdbcTemplate, ContactRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void createNewContact(Contact contact) {
        String query = "INSERT INTO contact (firstName, lastName, subject, content, user_id) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, contact.getFirstName(), contact.getLastName(), contact.getSubject(), contact.getContent(), contact.getUser_id());
    }


    public List<Contact> getAllContact() {
        String query = "SELECT * FROM contact";

        List<Contact> contacts = jdbcTemplate.query(query, rowMapper);

        return contacts;
    }

}

@Component
class ContactRowMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getInt("contact_id"));
        contact.setFirstName(rs.getString("firstName"));
        contact.setLastName(rs.getString("lastName"));
        contact.setSubject(rs.getString("subject"));
        contact.setContent(rs.getString("content"));
        contact.setUser_id(rs.getInt("user_id"));

        return contact;
    }
}
