package it.lpleo.management.camp.record.child;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;

public class SubscriptionRowMapperTest {

  @Test
  public void mapRow() throws SQLException {
    SubscriptionRowMapper rowMapper = new SubscriptionRowMapper();
    Subscription subscription = rowMapper.mapRow(createResultSet(), 7);

    assertThat(subscription.getId(), is(11L));
    assertThat(subscription.getChildId(), is(22L));
    assertThat(subscription.getCampId(), is(33L));
    assertThat(subscription.getWeekNumber(), is(3));
    assertThat(subscription.getFirstDay(), is(11));
    assertThat(subscription.getLastDay(), is(15));
    assertThat(subscription.getMonth(), is(12));
  }

  private ResultSet createResultSet() throws SQLException {
    ResultSet resultSet = mock(ResultSet.class);

    when(resultSet.getLong("id")).thenReturn(11L);
    when(resultSet.getLong("childId")).thenReturn(22L);
    when(resultSet.getLong("campId")).thenReturn(33L);
    when(resultSet.getInt("weekNumber")).thenReturn(3);
    when(resultSet.getInt("firstDay")).thenReturn(11);
    when(resultSet.getInt("lastDay")).thenReturn(15);
    when(resultSet.getInt("month")).thenReturn(12);

    return resultSet;
  }
}