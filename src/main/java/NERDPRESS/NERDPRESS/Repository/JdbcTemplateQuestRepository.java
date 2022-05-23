package NERDPRESS.NERDPRESS.Repository;

import NERDPRESS.NERDPRESS.Domain.Quest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateQuestRepository implements QuestRepositoryInterface {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateQuestRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Quest> questRowMapper() {
        return new RowMapper<Quest>() {
            @Override
            public Quest mapRow(ResultSet rs, int rowNum) throws SQLException {
                Quest quest = new Quest();
                quest.setQuest_id(rs.getInt("Quest_id"));
                quest.setLicense_id(rs.getInt("License_id"));
                quest.setQuest_title(rs.getString("Quest_title"));
                quest.setQ_one(rs.getString("Q_one"));
                quest.setQ_two(rs.getString("Q_two"));
                quest.setQ_three(rs.getString("Q_three"));
                quest.setQ_four(rs.getString("Q_four"));
                quest.setQuest_answer(rs.getInt("Quest_answer"));
                return quest;
            }
        };
    }

    @Override
    public void saveQuest(Quest q) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Quest_id", q.getQuest_id());
        parameters.put("License_id", q.getLicense_id());
        parameters.put("Quest_title", q.getQuest_title());
        parameters.put("Q_one", q.getQ_one());
        parameters.put("Q_two", q.getQ_two());
        parameters.put("Q_three", q.getQ_three());
        parameters.put("Q_four", q.getQ_four());
        parameters.put("Quest_answer", q.getQuest_answer());

        jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    }

    // License_id (자격증 ID) 에 해당하는 문제 모두 출력
    @Override
    public Quest findQuest(int license_id) {
        List<Quest> questList = jdbcTemplate.query("SELECT * FROM quest where License_id = ?", questRowMapper(), license_id);

        return questList.stream().findFirst().get();
    }
}

