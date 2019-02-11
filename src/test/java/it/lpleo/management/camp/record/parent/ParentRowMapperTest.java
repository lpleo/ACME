package it.lpleo.management.camp.record.parent;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;

public class ParentRowMapperTest {

  @Test
  public void mapRow() throws SQLException {
    ParentRowMapper parentRowMapper = new ParentRowMapper();
    Parent parent = parentRowMapper.mapRow(createResultSet(), 6);

    assertThat(parent.getId(), is(25L));
    assertThat(parent.getName(), is("CICCIO"));
    assertThat(parent.getSurname(), is("PASTICCIO"));
    assertThat(parent.getFiscalCode(), is("123456WEGM"));
    assertThat(parent.getEmail(), is("l@l.l"));
    assertThat(parent.getTelephoneNumber(), is("049123"));
  }

  private ResultSet createResultSet() throws SQLException {
    ResultSet set = mock(ResultSet.class);
    when(set.getLong("id")).thenReturn(25L);
    when(set.getString("name")).thenReturn("CICCIO");
    when(set.getString("surname")).thenReturn("PASTICCIO");
    when(set.getString("fiscalCode")).thenReturn("123456WEGM");
    when(set.getString("email")).thenReturn("l@l.l");
    when(set.getString("telephoneNumber")).thenReturn("049123");
    return set;
  }
}