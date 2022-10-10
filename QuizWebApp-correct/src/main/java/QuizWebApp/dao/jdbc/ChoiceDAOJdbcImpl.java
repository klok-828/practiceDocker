package QuizWebApp.dao.jdbc;

import QuizWebApp.dao.ChoiceDAO;
import QuizWebApp.domain.Choice;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("choiceDAOJdbcImpl")
public class ChoiceDAOJdbcImpl implements ChoiceDAO {

    JdbcTemplate jdbcTemplate;
    ChoiceRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public ChoiceDAOJdbcImpl(JdbcTemplate jdbcTemplate, ChoiceRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public String getAnswerByQuestionId(int id) {
        String query = "SELECT description FROM choice WHERE question_id = ? AND is_answer = 1";
        return jdbcTemplate.queryForObject(query, String.class, id);
    }
    public void createNewChoices(List<Choice> choiceList) {
        String query = "INSERT INTO choice (question_id, description, is_answer) values (?, ?, ?)";
        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Choice choice = choiceList.get(i);
                ps.setInt(1, choice.getQuestion_id());
                ps.setString(2, choice.getDescription());
                ps.setBoolean(3, choice.is_answer());
            }

            @Override
            public int getBatchSize() {
                return choiceList.size();
            }
        });
    }

    public List<Choice> getChoicesByQuestionId(int id) {
        String query = "SELECT * FROM choice WHERE question_id = ?";
        List<Choice> choices = jdbcTemplate.query(query, rowMapper, id);
        return choices;
    }
}

@Component
class ChoiceRowMapper implements RowMapper<Choice> {

    @Override
    public Choice mapRow(ResultSet rs, int rowNum) throws SQLException {
        Choice choice = new Choice();
        choice.setId(rs.getInt("choice_id"));
        choice.setQuestion_id(rs.getInt("question_id"));
        choice.setDescription(rs.getString("description"));
        choice.set_answer(rs.getBoolean("is_answer"));

        return choice;
    }
}
