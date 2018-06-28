package it.lpleo.management.camp.record.children;

import lombok.Data;

import java.util.List;

@Data
public class Registration {

  private Long id;
  private Long childrenId;
  private int year;
  private boolean anticipation;
  private boolean posticipation;
  private boolean deposit;
  private boolean bankTransfer;
  private boolean socialHelper;
  private String notes;
  private List<Subscription> subscriptions;
}
