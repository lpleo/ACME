package it.lpleo.management.camp.record;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.lpleo.management.camp.record.child.Allergy;
import it.lpleo.management.camp.record.child.AllergyRowMapper;
import it.lpleo.management.camp.record.child.Child;
import it.lpleo.management.camp.record.child.ChildRowMapper;
import it.lpleo.management.camp.record.child.Subscription;
import it.lpleo.management.camp.record.child.SubscriptionRowMapper;
import it.lpleo.management.camp.record.child.Type;
import it.lpleo.management.camp.record.parent.Parent;
import it.lpleo.management.camp.record.parent.ParentRowMapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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

  @Mock
  private SubscriptionRowMapper subscriptionRowMapper;

  @InjectMocks
  private PersonRepository personRepository;

  @Test
  public void getChild() throws ParseException {
    when(jdbcTemplate
        .queryForObject(anyString(), any(MapSqlParameterSource.class), eq(childRowMapper)))
        .thenReturn(createChild());

    Child child = personRepository.getChild(11L);

    assertEquals(simpleDateFormat().parse("11/11/2011"), child.getBirthDate());
    assertEquals("ABC123", child.getFiscalCode());
    assertEquals(new Long(11), child.getId());
    assertEquals("NAME", child.getName());
    assertEquals("SURNAME", child.getSurname());
  }

  @Test
  public void insertChild() throws ParseException {

    personRepository.insertChild(createChild());

    ArgumentCaptor<MapSqlParameterSource> captor = ArgumentCaptor
        .forClass(MapSqlParameterSource.class);

    verify(jdbcTemplate).update(anyString(), captor.capture());

    MapSqlParameterSource map = captor.getValue();

    assertEquals(simpleDateFormat().parse("11/11/2011"), map.getValue("birthDate"));
    assertEquals("ABC123", map.getValue("fiscalCode"));
    assertEquals("NAME", map.getValue("name"));
    assertEquals("SURNAME", map.getValue("surname"));

  }

  @Test
  public void insertParent() throws ParseException {

    personRepository.insertParent(createParent());

    ArgumentCaptor<MapSqlParameterSource> captor = ArgumentCaptor
        .forClass(MapSqlParameterSource.class);

    verify(jdbcTemplate).update(anyString(), captor.capture());

    MapSqlParameterSource map = captor.getValue();

    assertThat(map.getValue("fiscalCode"), is("CCCAAA55L18A456G"));
    assertThat(map.getValue("name"), is("PARENT_NAME"));
    assertThat(map.getValue("surname"), is("PARENT_SURNAME"));
    assertThat(map.getValue("email"), is("email@email.email"));
    assertThat(map.getValue("phoneNumber"), is("132456798"));
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

    assertEquals(11L, captor.getValue().getValue("childId"));

    assertEquals(2, result.size());
    assertEquals(new Long(16), result.get(1));
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

    assertEquals(15L, captor.getValue().getValue("id"));

    assertEquals(parent.getEmail(), "email@email.email");
    assertEquals(parent.getTelephoneNumber(), "132456798");
    assertEquals(parent.getFiscalCode(), "CCCAAA55L18A456G");
    assertEquals(new Long(15), parent.getId());
    assertEquals(parent.getName(), "PARENT_NAME");
    assertEquals(parent.getSurname(), "PARENT_SURNAME");
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

    assertEquals(11L, captor.getValue().getValue("childId"));

    assertEquals(1, allergies.size());

    Allergy allergy = allergies.get(0);

    assertEquals(Type.FOOD, allergy.getType());
    assertEquals(new Long(11), allergy.getChildId());
    assertEquals("DESCRIPTION", allergy.getDescription());
    assertEquals(new Long(18), allergy.getId());
    assertEquals("ALLERGY_NAME", allergy.getName());

  }

  @Test
  public void getChildren() throws ParseException {
    when(jdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), eq(childRowMapper)))
        .thenReturn(createChildren());

    List<Child> children = personRepository.getChildren(2008L);

    ArgumentCaptor<MapSqlParameterSource> captor = ArgumentCaptor
        .forClass(MapSqlParameterSource.class);

    verify(jdbcTemplate).query(anyString(), captor.capture(), eq(childRowMapper));

    assertEquals(2008L, captor.getValue().getValue("year"));

    assertEquals(1, children.size());

    Child child = children.get(0);
    assertEquals(simpleDateFormat().parse("11/11/2011"), child.getBirthDate());
    assertEquals("ABC123", child.getFiscalCode());
    assertEquals(new Long(11), child.getId());
    assertEquals("NAME", child.getName());
    assertEquals("SURNAME", child.getSurname());
  }

  @Test
  public void getSubscriptionsByChildAndCampId() {
    when(jdbcTemplate
        .query(anyString(), any(MapSqlParameterSource.class), eq(subscriptionRowMapper)))
        .thenReturn(
            Collections.singletonList(createSubscription()));

    List<Subscription> subscriptions = personRepository.getSubscriptions(33L, 22L);

    ArgumentCaptor<MapSqlParameterSource> parameterCaptor = ArgumentCaptor
        .forClass(MapSqlParameterSource.class);

    ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);

    verify(jdbcTemplate)
        .query(queryCaptor.capture(), parameterCaptor.capture(), eq(subscriptionRowMapper));

    assertThat(queryCaptor.getValue(), containsString(" AND "));

    assertThat(parameterCaptor.getValue().getValue("campId"), is(33L));
    assertThat(parameterCaptor.getValue().getValue("childId"), is(22L));

    Subscription subscription = subscriptions.get(0);
    assertThat(subscription.getId(), is(11L));
    assertThat(subscription.getChildId(), is(22L));
    assertThat(subscription.getCampId(), is(33L));
    assertThat(subscription.getWeekNumber(), is(3));
    assertThat(subscription.getFirstDay(), is(11));
    assertThat(subscription.getLastDay(), is(15));
    assertThat(subscription.getMonth(), is(12));
  }

  private Subscription createSubscription() {
    Subscription subscription = new Subscription();
    subscription.setId(11L);
    subscription.setChildId(22L);
    subscription.setCampId(33L);
    subscription.setWeekNumber(3);
    subscription.setFirstDay(11);
    subscription.setLastDay(15);
    subscription.setMonth(12);
    return subscription;
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