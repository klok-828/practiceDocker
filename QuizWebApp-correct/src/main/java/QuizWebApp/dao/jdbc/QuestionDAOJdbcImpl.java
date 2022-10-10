package QuizWebApp.dao.jdbc;

import QuizWebApp.dao.QuestionDAO;
import QuizWebApp.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("questionDAOJdbcImpl")
public class QuestionDAOJdbcImpl implements QuestionDAO {
    JdbcTemplate jdbcTemplate;
    QuestionRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuestionDAOJdbcImpl(JdbcTemplate jdbcTemplate, QuestionRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void updateQuestionStatusById(int id) {
        String query = "UPDATE question SET is_active = NOT is_active WHERE question_id = ?";
        jdbcTemplate.update(query, id);
    }
    public Question getQuestionById(int id) {
        String query = "SELECT * FROM question WHERE question_id = ?";
        List<Question> quizQuestions = jdbcTemplate.query(query, rowMapper, id);
        return quizQuestions.size() == 0 ? null : quizQuestions.get(0);
    }
    public void createNewQuestion(Question question) {
        String query = "INSERT INTO question (category, description, is_active) values (?, ?, ?)";
        jdbcTemplate.update(query, question.getCategory(), question.getDescription(), question.isActive());
    }

    public Integer getLatestId() {
        String query = "SELECT max(question_id) FROM question";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    public List<Question> getRandomQuestionsByCategory(String category) {
        String query = "SELECT * from question where category = ? AND is_active = 1 ORDER BY RAND() LIMIT 5";
        List<Question> questions = jdbcTemplate.query(query, rowMapper, category);
        return questions;
    }



    public List<Question> getAllQuestions() {
        String query = "SELECT * FROM question";

        List<Question> questions = jdbcTemplate.query(query, rowMapper);

        return questions;
    }

    public List<String> getAllCategory() {
        String query = "SELECT DISTINCT category FROM question";

        List<String> questionCategories = jdbcTemplate.queryForList(query, String.class);

        return questionCategories;
    }
}

@Component
class QuestionRowMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setId(rs.getInt("question_id"));
        question.setCategory(rs.getString("category"));
        question.setDescription(rs.getString("description"));
        question.setActive(rs.getBoolean("is_active"));

        return question;
    }
}
