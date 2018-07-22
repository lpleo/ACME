package it.lpleo.management.camp.record;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

import it.lpleo.management.camp.record.child.Child;
import it.lpleo.management.camp.record.child.ChildRowMapper;
import it.lpleo.management.camp.record.parent.ParentRowMapper;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

public class PersonRepositoryTest {

  @Mock
  private JdbcTemplate jdbcTemplate;

  @Mock
  private ChildRowMapper childRowMapper;

  @Mock
  private ParentRowMapper parentRowMapper;

  @Test
  public void getChild() {
    when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(childRowMapper)))
        .thenReturn(createChild());

    //TODO continue from here

  }

  private Child createChild() {
    return null;
  }

  @Test
  public void insertChild() {
  }

  @Test
  public void getParentIdsFromChild() {
  }

  @Test
  public void getParent() {
  }

  @Test
  public void getAllergiesByChildId() {
  }


}