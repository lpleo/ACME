package it.lpleo.management.camp.record;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.lpleo.management.camp.record.child.Allergy;
import it.lpleo.management.camp.record.child.Child;
import it.lpleo.management.camp.record.child.Type;
import it.lpleo.management.camp.record.parent.Parent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  @InjectMocks
  private PersonService personService;

  @Test
  public void getChild() throws ParseException {

    Long id = 123L;

    when(personRepository.getChild(id)).thenReturn(createChild(id));

    Child child = personService.getChild(id);

    assertEquals(createDate("10", "10", "2010"), child.getBirthDate());
    assertEquals(123L, (long) child.getId());
    assertEquals("PIPPO", child.getName());
    assertEquals("PLUTO", child.getSurname());
    assertEquals("CCCAAABBB", child.getFiscalCode());
  }

  @Test
  public void getChildren() throws ParseException {

    Long campYear = 2010L;

    when(personRepository.getChildren(campYear))
        .thenReturn(Arrays.asList(new Child(), createChild(123L)));

    List<Child> children = personService.getChildren(campYear);

    Assert.assertEquals(2, children.size());

    Child child = children.get(1);

    assertEquals(createDate("10", "10", "2010"), child.getBirthDate());
    assertEquals(123L, (long) child.getId());
    assertEquals("PIPPO", child.getName());
    assertEquals("PLUTO", child.getSurname());
    assertEquals("CCCAAABBB", child.getFiscalCode());
  }

  @Test
  public void insertChild() throws ParseException {
    Child child = createChild(123L);
    personService.insertChild(child);
    verify(personRepository).insertChild(child);
  }

  @Test
  public void getParent() {
    Long id = 123L;
    when(personRepository.getParent(id)).thenReturn(createParent(id));
    Parent parent = personRepository.getParent(id);

    assertEquals("hello@people.com", parent.getEmail());
    assertEquals("333 222 111", parent.getTelephoneNumber());
    assertEquals(123L, (long) parent.getId());
    assertEquals("PIPPO", parent.getName());
    assertEquals("PLUTO", parent.getSurname());
    assertEquals("CCCAAABBB", parent.getFiscalCode());
  }

  @Test
  public void getAllergiesByChildId() {
    Long id = 123L;
    when(personRepository.getAllergiesByChildId(id)).thenReturn(createAllergies(id));
    List<Allergy> allergyList = personRepository.getAllergiesByChildId(id);

    assertEquals(2, allergyList.size());
    Allergy allergy = allergyList.get(0);

    assertEquals(Type.FOOD, allergy.getType());
    assertEquals("FOOD ALLERGY", allergy.getName());
    assertEquals((Long) 456L, allergy.getId());
    assertEquals("DESC", allergy.getDescription());
    assertEquals(id, allergy.getChildId());

  }

  private List<Allergy> createAllergies(Long id) {
    List<Allergy> allergies = new ArrayList<>();
    Allergy allergy = new Allergy();
    allergy.setType(Type.FOOD);
    allergy.setName("FOOD ALLERGY");
    allergy.setId(456L);
    allergy.setDescription("DESC");
    allergy.setChildId(id);
    allergies.add(allergy);
    allergies.add(new Allergy());
    return allergies;
  }

  private Child createChild(Long id) throws ParseException {
    Child child = new Child();
    child.setBirthDate(createDate("10", "10", "2010"));
    createPerson(id, child);
    return child;
  }

  private Parent createParent(Long id) {
    Parent parent = new Parent();
    parent.setTelephoneNumber("333 222 111");
    parent.setEmail("hello@people.com");
    createPerson(id, parent);
    return parent;
  }

  private void createPerson(Long id, Person person) {
    person.setId(id);
    person.setFiscalCode("CCCAAABBB");
    person.setName("PIPPO");
    person.setSurname("PLUTO");
  }

  private Date createDate(String day, String month, String year) throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    return simpleDateFormat.parse(day + "-" + month + "-" + year);
  }
}
