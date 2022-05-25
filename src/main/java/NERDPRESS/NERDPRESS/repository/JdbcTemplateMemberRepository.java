package NERDPRESS.NERDPRESS.Repository;

import NERDPRESS.NERDPRESS.Domain.Member;
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

//@Repository
public class JdbcTemplateMemberRepository implements MemberRepositoryInterface {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private RowMapper<Member> MemberRowMapper() {
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet res, int rowNum) throws SQLException {
                Member output = new Member();
                output.setId(res.getInt("id"));
                output.setUserId(res.getString("userId"));
                output.setName(res.getString("name"));
                output.setPW(res.getString("PW"));
                output.setGrade(res.getByte("grade"));
                output.setMale(res.getBoolean("male"));
                output.setBirthDate(res.getInt("birthdate"));
                output.setRecommend(res.getInt("recommand"));
                return output;
            }
        };
    }
    @Override
    public void saveMember(Member m) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", "auto_mem_id.NEXTVAL");
        parameters.put("name", m.getName());
        parameters.put("userId", m.getUserId());
        parameters.put("Male", m.getMale());
        parameters.put("PW", m.getPW());
        parameters.put("birthDate", m.getBirthDate());
        parameters.put("grade", m.getGrade());
        parameters.put("EMail",m.getEmail());
        jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    }

    @Override
    public Member getById(int id) {
        List<Member> output = jdbcTemplate.query("SELECT * FROM member WHERE id = ?", MemberRowMapper(), id);
        return output.stream().findFirst().get();
    }

    @Override
    public Member getByUserId(String userId) {
        List<Member> output = jdbcTemplate.query("SELECT * FROM member WHERE Userid = ?", MemberRowMapper(), userId);
        return output.stream().findFirst().get();
    }

    @Override
    public List<Member> getAllDomains() {
        return jdbcTemplate.query("SELECT * FROM member", MemberRowMapper());
    }

    @Override
    public List<Member> pagingList(int page) {

        List<Member> memberList = jdbcTemplate.query("Select id, userId, email, recommand from (Select rownum as rn, id, userId, email from member) where rn between " + ((page -1) * 10 + 1)  +"and" + 19, MemberRowMapper());


        return memberList;
    }

    @Override
    public List<Member> pagingWriterList(int page){

        List<Member> writerList = jdbcTemplate.query("Select id, userId, email, recommand from (Select rownum as rn, id, userId, email from member) where grade=1 AND (rn between" + ((page -1) * 10 + 1)  + "and" + 19 +")", MemberRowMapper());

        return writerList;
    }


}
