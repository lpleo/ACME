package it.lpleo.management.camp.record.children;

import lombok.Data;

enum Type {
  FOOD, OTHER
}

@Data
public class Allergy {

  private Long id;
  private String name;
  private String description;
  private Type type;
}
