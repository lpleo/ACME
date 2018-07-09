package it.lpleo.management.camp.record.child;

import it.lpleo.management.camp.record.Person;
import java.util.Date;
import lombok.Data;

@Data
public class Child extends Person {
  private Date birthDate;
}
