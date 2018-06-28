package it.lpleo.management.camp.record;

import lombok.Data;

@Data
public class Parent extends Record {

  private Long id;
  private String email;
  private String telephoneNumber;
}
