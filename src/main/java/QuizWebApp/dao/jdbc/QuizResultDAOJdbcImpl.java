package QuizWebApp.dao.jdbc;

import QuizWebApp.dao.QuizResultDAO;
import QuizWebApp.domain.QuizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("quizResultDAOJdbcImpl")
public class QuizResultDAOJdbcImpl implements QuizResultDAO {
    JdbcTemplate jdbcTemplate;
    QuizResultRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuizResultDAOJdbcImpl(JdbcTemplate jdbcTemplate, QuizResultRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void createNewQuizResult(QuizResult quizResult) {
        String query = "INSERT INTO quizresult (category, user_id, start_time, end_time, is_pass) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, quizResult.getCategory(), quizResult.getUserId(), quizResult.getStartTime(), quizResult.getEndTime(), quizResult.isPass());
    }

    public List<QuizResult> getQuizResultByCategoryAndUserId(String category, int userId) {
        String query = "SELECT * FROM quizresult WHERE category = ? AND user_id = ?";
        return jdbcTemplate.query(query, rowMapper, category, userId);
    }
    public List<QuizResult> getQuizResultByCategory(String category) {
        String query = "SELECT * FROM quizresult WHERE category = ?";
        List<QuizResult> quizResults = jdbcTemplate.query(query, rowMapper, category);
        return quizResults;
    }

    public Integer getLatestId() {
        String query = "SELECT max(quizresult_id) FROM quizresult";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    public QuizResult getQuizResultByQuizId(int id) {
        String query = "SELECT * FROM quizresult WHERE quizresult_id = ?";
        List<QuizResult> quizResults = jdbcTemplate.query(query, rowMapper, id);
        return quizResults.size() == 0 ? null : quizResults.get(0);
    }
    public List<QuizResult> getQuizResultByUserId(int id) {
        String query = "SELECT * FROM quizresult WHERE user_id = ? ORDER BY start_time DESC";
        return jdbcTemplate.query(query, rowMapper, id);
    }
    public List<QuizResult> getAllQuizResult() {
        String query = "SELECT * FROM quizresult ORDER BY start_time DESC";

        List<QuizResult> quizResults = jdbcTemplate.query(query, rowMapper);

        return quizResults;
    }
}

@Component
class QuizResultRowMapper implements RowMapper<QuizResult> {

    @Override
    public QuizResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuizResult quizResult = new QuizResult();
        quizResult.setId(rs.getInt("quizresult_id"));
        quizResult.setCategory(rs.getString("category"));
        quizResult.setUserId(rs.getInt("user_id"));
        quizResult.setStartTime(rs.getTimestamp("start_time"));
        quizResult.setEndTime(rs.getTimestamp("end_time"));
        quizResult.setPass(rs.getBoolean("is_pass"));

        return quizResult;
    }
}