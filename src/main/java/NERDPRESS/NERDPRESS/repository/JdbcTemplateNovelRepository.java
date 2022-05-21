package NERDPRESS.NERDPRESS.repository;

import NERDPRESS.NERDPRESS.domain.Novel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateNovelRepository implements NovelRepositoryInterface{

    // jdbcTemplate, sql 자동 사용 클래스 가져오기
    private JdbcTemplate jdbcTemplate;

    // 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다. 생성자 or setter or 멤버필드
    // 생성자에 넣는 것이 가장 좋다고 함
    @Autowired
    public JdbcTemplateNovelRepository(DataSource dataSource){
        // DataSource 클래스는 정해진 만큼 사용하고 자동으로 닫아주는 클래스
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Novel> novelRowMapper(){
        return new RowMapper<Novel>() {
            @Override
            public Novel mapRow(ResultSet rs, int rowNum) throws SQLException {
                Novel novel = new Novel();
                novel.setId(rs.getInt("id"));
                novel.setDate(rs.getInt("date"));
                novel.setTitle(rs.getString("title"));
                novel.setGenre(rs.getString("genre"));
                novel.setContent(rs.getString("content"));
                return novel;
            }
        };
    }


    @Override
    public void saveNovel(Novel n) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("novel").usingGeneratedKeyColumns("id");
        jdbcInsert.withTableName("novel").usingGeneratedKeyColumns("date");
        jdbcInsert.withTableName("novel").usingGeneratedKeyColumns("title");
        jdbcInsert.withTableName("novel").usingGeneratedKeyColumns("genre");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", n.getId());
        parameters.put("date", n.getDate());
        parameters.put("title", n.getTitle());
        parameters.put("genre", n.getGenre());

        jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

    }

    // 제목으로 소설찾기
    @Override
    public Novel findNovel(String title) {
        List<Novel> novelList = jdbcTemplate.query("SELECT * FROM novel where id = ?", novelRowMapper(), title);


        return novelList.stream().findFirst().get();
    }

    @Override
    public List<Novel> topTwenty() {
        return null;
    }


}
