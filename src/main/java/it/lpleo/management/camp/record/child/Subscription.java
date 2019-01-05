package it.lpleo.management.camp.record.child;

import lombok.Data;

@Data
public class Subscription {

  private Long id;
  private Long childId;
  private Long campId;
  private Integer month;
  private Integer firstDay;
  private Integer lastDay;
  private Integer weekNumber;
}
