package it.lpleo.management.camp.camp;

import lombok.Data;

@Data
public class Camp {
    private Long id;
    private String name;
    private Long year;
    private boolean active;
}
