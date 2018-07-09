package it.lpleo.management.camp.record.child;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AllergyRowMapper implements RowMapper<Allergy> {

  @Override
  public Allergy mapRow(ResultSet resultSet, int i) throws SQLException {
    Allergy allergy = new Allergy();
    allergy.setId(resultSet.getLong("id"));
    allergy.setChildId(resultSet.getLong("childId"));
    allergy.setDescription(resultSet.getString("description"));
    allergy.setName(resultSet.getString("name"));
    String type = resultSet.getString("type");

    if (type != null) {
      if (Type.FOOD.name().compareTo(type) == 0) {
        allergy.setType(Type.FOOD);
      }

      if (Type.OTHER.name().compareTo(type) == 0) {
        allergy.setType(Type.OTHER);
      }
    }

    return allergy;
  }
}
