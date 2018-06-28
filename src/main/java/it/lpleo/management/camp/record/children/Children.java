package it.lpleo.management.camp.record.children;

import it.lpleo.management.camp.record.Record;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Children extends Record {

  private List<Allergy> allergies;
  private Date birthDate;
  private List<Registration> registrations;
}
