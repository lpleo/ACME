package it.lpleo.management.camp.record.child;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ChildRowMapper implements RowMapper<Child> {

  @Override
  public Child mapRow(ResultSet resultSet, int i) throws SQLException {
    Child child = new Child();
    child.setId(resultSet.getLong("id"));
    child.setName(resultSet.getString("name"));
    child.setSurname(resultSet.getString("surname"));
    child.setFiscalCode(resultSet.getString("fiscalCode"));
    child.setBirthDate(resultSet.getDate("birthDate"));
    return child;
  }
}
