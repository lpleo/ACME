package it.lpleo.management.camp.camp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CampRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private CampRowMapper campRowMapper;

  private static final String SELECT_ALL_CAMPS = "SELECT * FROM CAMP";
  private static final String SELECT_SINGLE_CAMP = "SELECT * FROM CAMP WHERE id = ?";
  private static final String INSERT_NEW_CAMP = "INSERT INTO CAMP(name,`year`,active) VALUES (?,?,?)";


  public List<Camp> getAllCamps() {
    return jdbcTemplate.query(SELECT_ALL_CAMPS, campRowMapper);
  }

  public void insertNewCamp(Camp newCamp) {
    jdbcTemplate.update(INSERT_NEW_CAMP, newCamp.getName(), newCamp.getYear(), newCamp.isActive());
  }

  public Camp getCamp(Long campId) {
    return jdbcTemplate
        .queryForObject(SELECT_SINGLE_CAMP, new Object[]{campId}, campRowMapper);
  }
}
