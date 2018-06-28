package it.lpleo.management.camp.camp;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CampRepositoryRowMapper implements RowMapper<Camp> {

  @Override
  public Camp mapRow(ResultSet resultSet, int i) throws SQLException {
    Camp camp = new Camp();
    camp.setId(resultSet.getLong("id"));
    camp.setYear(resultSet.getLong("year"));
    camp.setName(resultSet.getString("name"));
    camp.setActive(resultSet.getBoolean("active"));
    return camp;
  }
}
