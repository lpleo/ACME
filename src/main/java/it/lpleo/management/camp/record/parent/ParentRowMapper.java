package it.lpleo.management.camp.record.parent;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ParentRowMapper implements RowMapper<Parent> {

  @Override
  public Parent mapRow(ResultSet resultSet, int i) throws SQLException {
    Parent parent = new Parent();
    parent.setId(resultSet.getLong("id"));
    parent.setName(resultSet.getString("name"));
    parent.setSurname(resultSet.getString("surname"));
    parent.setFiscalCode(resultSet.getString("fiscalCode"));
    parent.setEmail(resultSet.getString("email"));
    parent.setTelephoneNumber(resultSet.getString("telephoneNumber"));
    return parent;
  }
}
