package it.lpleo.management.camp.record;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.lpleo.management.camp.record.child.Allergy;
import it.lpleo.management.camp.record.child.AllergyRowMapper;
import it.lpleo.management.camp.record.child.Child;
import it.lpleo.management.camp.record.child.ChildRowMapper;
import it.lpleo.management.camp.record.child.Type;
import it.lpleo.management.camp.record.parent.Parent;
import it.lpleo.management.camp.record.parent.ParentRowMapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
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

  @Mock
  private AllergyRowMapper allergyRowMapper;

  @InjectMocks
  private PersonRepository personRepository;

  @Test
  public void getChild() throws ParseException {
    when(jdbcTemplate
        .queryForObject(anyString(), any(MapSqlParameterSource.class), eq(childRowMapper)))
        .thenReturn(createChild());

    Child child = personRepository.getChild(11L);

    Assert.assertEquals(simpleDateFormat().parse("11/11/2011"), child.getBirthDate());
    Assert.assertEquals("ABC123", child.getFiscalCode());
    Assert.assertEquals(new Long(11), child.getId());
    Assert.assertEquals("NAME", child.getName());
    Assert.assertEquals("SURNAME", child.getSurname());
  }

  @Test
  public void insertChild() throws ParseException {

    personRepository.insertChild(createChild());

    ArgumentCaptor<MapSqlParameterSource> captor = ArgumentCaptor
        .forClass(MapSqlParameterSource.class);

    verify(jdbcTemplate).update(anyString(), captor.capture());

    MapSqlParameterSource map = captor.getValue();

    Assert.assertEquals(simpleDateFormat().parse("11/11/2011"), map.getValue("birthDate"));
    Assert.assertEquals("ABC123", map.getValue("fiscalCode"));
    Assert.assertEquals("NAME", map.getValue("name"));
    Assert.assertEquals("SURNAME", map.getValue("surname"));

  }

  @Test
  public void getParentIdsFromChild() {
    when(jdbcTemplate
        .queryForList(anyString(), any(MapSqlParameterSource.class), eq(Long.class)))
        .thenReturn(Arrays.asList(15L, 16L));

    List<Long> result = personRepository.getParentIdsFromChild(11L);

    ArgumentCaptor<MapSqlParameterSource> captor = ArgumentCaptor
        .forClass(MapSqlParameterSource.class);

    verify(jdbcTemplate).queryForList(anyString(), captor.capture(), eq(Long.class));

    Assert.assertEquals(11L, captor.getValue().getValue("childId"));

    Assert.assertEquals(2, result.size());
    Assert.assertEquals(new Long(16), result.get(1));
  }

  @Test
  public void getParent() {
    when(jdbcTemplate
        .queryForObject(anyString(), any(MapSqlParameterSource.class), eq(parentRowMapper)))
        .thenReturn(createParent());

    Parent parent = personRepository.getParent(15L);

    ArgumentCaptor<MapSqlParameterSource> captor = ArgumentCaptor
        .forClass(MapSqlParameterSource.class);

    verify(jdbcTemplate).queryForObject(anyString(), captor.capture(), eq(parentRowMapper));

    Assert.assertEquals(15L, captor.getValue().getValue("id"));

    Assert.assertEquals(parent.getEmail(), "email@email.email");
    Assert.assertEquals(parent.getTelephoneNumber(), "132456798");
    Assert.assertEquals(parent.getFiscalCode(), "CCCAAA55L18A456G");
    Assert.assertEquals(new Long(15), parent.getId());
    Assert.assertEquals(parent.getName(), "PARENT_NAME");
    Assert.assertEquals(parent.getSurname(), "PARENT_SURNAME");
  }

  @Test
  public void getAllergiesByChildId() {
    when(jdbcTemplate
        .query(anyString(), any(MapSqlParameterSource.class), eq(allergyRowMapper)))
        .thenReturn(createAllergies());

    List<Allergy> allergies = personRepository.getAllergiesByChildId(11L);

    ArgumentCaptor<MapSqlParameterSource> captor = ArgumentCaptor
        .forClass(MapSqlParameterSource.class);

    verify(jdbcTemplate).query(anyString(), captor.capture(), eq(allergyRowMapper));

    Assert.assertEquals(11L, captor.getValue().getValue("childId"));

    Assert.assertEquals(1, allergies.size());

    Allergy allergy = allergies.get(0);

    Assert.assertEquals(Type.FOOD, allergy.getType());
    Assert.assertEquals(new Long(11), allergy.getChildId());
    Assert.assertEquals("DESCRIPTION", allergy.getDescription());
    Assert.assertEquals(new Long(18), allergy.getId());
    Assert.assertEquals("ALLERGY_NAME", allergy.getName());

  }

  @Test
  public void getChildren() throws ParseException {
    when(jdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), eq(childRowMapper)))
        .thenReturn(createChildren());

    List<Child> children = personRepository.getChildren(2008L);

    ArgumentCaptor<MapSqlParameterSource> captor = ArgumentCaptor
        .forClass(MapSqlParameterSource.class);

    verify(jdbcTemplate).query(anyString(), captor.capture(), eq(childRowMapper));

    Assert.assertEquals(2008L, captor.getValue().getValue("year"));

    Assert.assertEquals(1, children.size());

    Child child = children.get(0);
    Assert.assertEquals(simpleDateFormat().parse("11/11/2011"), child.getBirthDate());
    Assert.assertEquals("ABC123", child.getFiscalCode());
    Assert.assertEquals(new Long(11), child.getId());
    Assert.assertEquals("NAME", child.getName());
    Assert.assertEquals("SURNAME", child.getSurname());
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

  private List<Child> createChildren() throws ParseException {
    List<Child> children = new ArrayList<>();
    children.add(createChild());
    return children;
  }

  private Parent createParent() {
    Parent parent = new Parent();
    parent.setEmail("email@email.email");
    parent.setTelephoneNumber("132456798");
    parent.setFiscalCode("CCCAAA55L18A456G");
    parent.setId(15L);
    parent.setName("PARENT_NAME");
    parent.setSurname("PARENT_SURNAME");
    return parent;
  }

  private List<Allergy> createAllergies() {
    List<Allergy> allergies = new ArrayList<>();
    allergies.add(createAllergy());
    return allergies;
  }

  private Allergy createAllergy() {
    Allergy allergy = new Allergy();
    allergy.setType(Type.FOOD);
    allergy.setChildId(11L);
    allergy.setDescription("DESCRIPTION");
    allergy.setId(18L);
    allergy.setName("ALLERGY_NAME");
    return allergy;
  }

  private SimpleDateFormat simpleDateFormat() {
    return new SimpleDateFormat("dd/MM/yyyy");
  }


}