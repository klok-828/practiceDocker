package QuizWebApp.dao.jdbc;

import QuizWebApp.dao.QuestResultDAO;
import QuizWebApp.domain.QuestResult;
import org.springframework.beans.factory.annotation.Autowired;
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

@Repository
public class QuestResultDAOJdbcImpl implements QuestResultDAO {
    JdbcTemplate jdbcTemplate;
    QuestResultRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuestResultDAOJdbcImpl(JdbcTemplate jdbcTemplate, QuestResultRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<QuestResult> getQuestResultByQuizId(int id) {
        String query = "SELECT * FROM questresult WHERE quizresult_id = ?";
        List<QuestResult> questResults = jdbcTemplate.query(query, rowMapper, id);
        return questResults;
    }
    public void createQuestResults(List<QuestResult> questResultListList) {
        String query = "INSERT INTO questresult (quizresult_id, question_id, user_choice) values (?, ?, ?)";
        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                QuestResult questResult = questResultListList.get(i);
                ps.setInt(1, questResult.getQuizResultId());
                ps.setInt(2, questResult.getQuestionId());
                ps.setString(3, questResult.getUserChoice());
            }

            @Override
            public int getBatchSize() {
                return questResultListList.size();
            }
        });
    }
}


@Component
class QuestResultRowMapper implements RowMapper<QuestResult> {

    @Override
    public QuestResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuestResult questResult = new QuestResult();
        questResult.setId(rs.getInt("questresult_id"));
        questResult.setQuestionId(rs.getInt("question_id"));
        questResult.setUserChoice(rs.getString("user_choice"));
        questResult.setQuizResultId(rs.getInt("quizresult_id"));

        return questResult;
    }
}
