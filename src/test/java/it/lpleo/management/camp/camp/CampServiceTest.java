package it.lpleo.management.camp.camp;

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
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CampServiceTest {

  @Mock
  private CampRepository campRepository;

  @InjectMocks
  private CampService campService;

  @Test
  public void getAllCampsTest() throws SQLException {

    when(campRepository.getAllCamps()).thenReturn(createCampList());

    List<Camp> camps = campService.getAllCamps();

    Assert.assertEquals(1, camps.size());
    Assert.assertEquals("name", camps.get(0).getName());
    Assert.assertEquals(new Long(1), camps.get(0).getYear());
    Assert.assertFalse(camps.get(0).isActive());
    Assert.assertEquals(new Long(2), camps.get(0).getId());
  }

  @Test
  public void insertNewCampTest() {
    campService.insertCamp(createCamp());

    ArgumentCaptor<Camp> captor = ArgumentCaptor.forClass(Camp.class);

    verify(campRepository).insertCamp(captor.capture());

    Assert.assertEquals("name", captor.getValue().getName());
    Assert.assertEquals(new Long(1), captor.getValue().getYear());
    Assert.assertFalse(captor.getValue().isActive());
    Assert.assertEquals(new Long(2), captor.getValue().getId());
  }

  @Test
  public void getCampTest() {
    when(campRepository.getCamp(2L)).thenReturn(createCamp());

    Camp camp = campService.getCamp(2L);

    Assert.assertEquals("name", camp.getName());
    Assert.assertEquals(new Long(1), camp.getYear());
    Assert.assertFalse(camp.isActive());
    Assert.assertEquals(new Long(2), camp.getId());
  }

  @Test
  public void getActiveCampTest() {
    Camp testCamp = createCamp();
    testCamp.setActive(true);
    when(campRepository.getActiveCamp()).thenReturn(testCamp);

    Camp camp = campService.getActiveCamp();

    Assert.assertEquals("name", camp.getName());
    Assert.assertEquals(new Long(1), camp.getYear());
    Assert.assertTrue(camp.isActive());
    Assert.assertEquals(new Long(2), camp.getId());
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