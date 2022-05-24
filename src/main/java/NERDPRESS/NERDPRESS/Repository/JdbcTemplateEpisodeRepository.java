package NERDPRESS.NERDPRESS.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class JdbcTemplateEpisodeRepository implements EpisodeRepositoryInterface {
    
    //Datasource는 springbean이다
    private DataSource dataSource;
    public int index = 0;

    @Autowired
    public JdbcTemplateEpisodeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveEpisode() {
        String sql = "INSERT INTO episodes(subtitle,content,writer,finish,check) values(?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            //e.setId(index++);

            //pstmt.setString(); // 1,m.getID()
            //pstmt.setString(); // m.getName());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        close(conn);
    }

        private void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
