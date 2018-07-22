package it.lpleo.management.camp.record.child;

import lombok.Data;

@Data
public class Allergy {

  private Long id;
  private Long childId;
  private String name;
  private String description;
  private Type type;
}
