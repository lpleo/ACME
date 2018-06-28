package it.lpleo.management.camp.camp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CampRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final String SELECT_ALL_CAMPS = "SELECT * FROM CAMP";


  public List<Camp> retrieveAllCamps() {
    return jdbcTemplate.query(SELECT_ALL_CAMPS, new Object[]{}, (rs, rowNum) -> {
          Camp c = new Camp();
          c.setId(rs.getLong("id"));
          c.setYear(rs.getLong("year"));
          c.setName(rs.getString("name"));
          c.setActive(rs.getBoolean("active"));
          return c;
        }
    );
  }
}
