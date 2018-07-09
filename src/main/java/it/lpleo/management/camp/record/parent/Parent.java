package it.lpleo.management.camp.record.parent;

import it.lpleo.management.camp.record.Person;
import lombok.Data;

@Data
public class Parent extends Person {
  private String email;
  private String telephoneNumber;
}
