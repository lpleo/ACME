package it.lpleo.management.camp.camp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
public class CampRepositoryTest {

  @Mock
  private JdbcTemplate jdbcTemplate;

  @Mock
  private CampRowMapper campRowMapper;

  @InjectMocks
  private CampRepository campRepository;

  @Test
  public void retrieveAllCamps() throws SQLException {

    when(jdbcTemplate.query(anyString(), any(Object[].class), Mockito.eq(campRowMapper)))
        .thenReturn(createCampList());

    List<Camp> camps = campRepository.getAllCamps();

    assertEquals(1, camps.size());
    assertEquals("name", camps.get(0).getName());
    assertEquals(new Long(1), camps.get(0).getYear());
    Assert.assertFalse(camps.get(0).isActive());
    assertEquals(new Long(2), camps.get(0).getId());
  }

  @Test
  public void insertNewCamp() {

    campRepository.insertCamp(createCamp());

    ArgumentCaptor<Object[]> captor = ArgumentCaptor.forClass(Object[].class);

    verify(jdbcTemplate).update(anyString(), captor.capture());

    assertEquals("name", captor.getAllValues().get(0));
    assertEquals(1L, captor.getAllValues().get(1));
    assertEquals(false, captor.getAllValues().get(2));
  }

  private List<Camp> createCampList() {
    ArrayList<Camp> camps = new ArrayList<>();
    camps.add(createCamp());
    return camps;
  }

  private Camp createCamp() {
    Camp camp = new Camp();
    camp.setName("name");
    camp.setYear(1L);
    camp.setActive(false);
    camp.setId(2L);
    return camp;
  }
}