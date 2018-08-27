package it.lpleo.management.camp.record;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

import it.lpleo.management.camp.record.child.Child;
import it.lpleo.management.camp.record.child.ChildRowMapper;
import it.lpleo.management.camp.record.parent.ParentRowMapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
public class PersonRepositoryTest {

  @Mock
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Mock
  private ChildRowMapper childRowMapper;

  @Mock
  private ParentRowMapper parentRowMapper;

  @InjectMocks
  private PersonRepository personRepository;

  @Test
  public void getChild() throws ParseException {
    when(jdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), eq(childRowMapper)))
        .thenReturn(createChild());

    Child child = personRepository.getChild(11L);

    Assert.assertEquals(simpleDateFormat().parse("11/11/2011"),child.getBirthDate());
    Assert.assertEquals("ABC123",child.getFiscalCode());
    Assert.assertEquals(new Long(11),child.getId());
    Assert.assertEquals("NAME",child.getName());
    Assert.assertEquals("SURNAME",child.getSurname());
  }

  @Test
  public void insertChild() {
    //TODO
  }

  @Test
  public void getParentIdsFromChild() {
    //TODO
  }

  @Test
  public void getParent() {
    //TODO
  }

  @Test
  public void getAllergiesByChildId() {
  }

  private Child createChild() throws ParseException {
    Child child = new Child();
    Date date = simpleDateFormat().parse("11/11/2011");
    child.setBirthDate(date);
    child.setFiscalCode("ABC123");
    child.setId(11L);
    child.setName("NAME");
    child.setSurname("SURNAME");
    return child;
  }

  private SimpleDateFormat simpleDateFormat() {
    return new SimpleDateFormat("dd/MM/yyyy");
  }


}