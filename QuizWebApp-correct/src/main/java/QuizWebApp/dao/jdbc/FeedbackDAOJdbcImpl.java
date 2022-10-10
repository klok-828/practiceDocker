package QuizWebApp.dao.jdbc;

import QuizWebApp.dao.FeedbackDAO;
import QuizWebApp.domain.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("feedbackDAOJdbcImpl")
public class FeedbackDAOJdbcImpl implements FeedbackDAO {
    JdbcTemplate jdbcTemplate;
    FeedbackRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public FeedbackDAOJdbcImpl(JdbcTemplate jdbcTemplate, FeedbackRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Feedback createNewFeedback(Feedback feedback) {
        String query = "INSERT INTO feedback (rating, content, fb_date) values (?, ?, ?)";
        jdbcTemplate.update(query, feedback.getRating(), feedback.getContent(), feedback.getFb_date());
        return null;
    }

    public List<Feedback> getAllFeedback() {
        String query = "SELECT * FROM feedback";

        List<Feedback> feedbacks = jdbcTemplate.query(query, rowMapper);

        return feedbacks;
    }

    @Override
    public boolean isFeedbackIdValid(int id) {
        return false;
    }


}

@Component
class FeedbackRowMapper implements RowMapper<Feedback> {

    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(rs.getInt("feedback_id"));
        feedback.setRating(rs.getInt("rating"));
        feedback.setContent(rs.getString("content"));
        feedback.setFb_date(rs.getTimestamp("fb_date"));

        return feedback;
    }
}
