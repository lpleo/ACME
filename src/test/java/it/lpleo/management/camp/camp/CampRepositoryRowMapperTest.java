package it.lpleo.management.camp.camp;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CampRepositoryRowMapperTest {

  @Test
  public void parseCampFromJdbcResultSet() throws SQLException {
    CampRepositoryRowMapper campRepositoryParser = new CampRepositoryRowMapper();
    Camp camp = campRepositoryParser.mapRow(createResulSet(), 5);

    Assert.assertEquals("PIPPO", camp.getName());
    Assert.assertEquals(new Long(1), camp.getId());
    Assert.assertEquals(new Long(2), camp.getYear());
    Assert.assertTrue(camp.isActive());
  }

  private ResultSet createResulSet() throws SQLException {
    ResultSet resultSet = Mockito.mock(ResultSet.class);

    when(resultSet.getString("name")).thenReturn("PIPPO");
    when(resultSet.getLong("id")).thenReturn(1L);
    when(resultSet.getLong("year")).thenReturn(2L);
    when(resultSet.getBoolean("active")).thenReturn(true);

    return resultSet;
  }
}