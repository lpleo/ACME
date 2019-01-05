package it.lpleo.management.camp.record.child;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionRowMapper implements RowMapper<Subscription> {

  @Override
  public Subscription mapRow(ResultSet resultSet, int i) throws SQLException {
    Subscription subscription = new Subscription();
    subscription.setId(resultSet.getLong("id"));
    subscription.setCampId(resultSet.getLong("campId"));
    subscription.setChildId(resultSet.getLong("childId"));
    subscription.setMonth(resultSet.getInt("month"));
    subscription.setFirstDay(resultSet.getInt("firstDay"));
    subscription.setLastDay(resultSet.getInt("lastDay"));
    subscription.setWeekNumber(resultSet.getInt("weekNumber"));
    return subscription;
  }
}
