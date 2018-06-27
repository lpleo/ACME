package it.lpleo.management.camp.record.children;

import lombok.Data;

@Data
public class Subscription {
    private Long id;
    private Integer month;
    private Integer firstDay;
    private Integer lastDay;
    private Integer weekNumber;
}
