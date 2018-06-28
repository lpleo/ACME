package it.lpleo.management.camp.camp;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Log4j2
@RestController
@RequestMapping("/camp")
public class CampController {

  @PostMapping
  public void insertNewCamp(@RequestBody Camp camp) {

  }

  @GetMapping
  public List<Camp> getAllCamps() {
    log.info("get all camps");
    return new ArrayList<>();
  }

  @GetMapping("/{campId}")
  public Camp getCamp(@PathVariable String campId) {
    log.info("get camp");
    return new Camp();
  }

  @DeleteMapping("/{campId}")
  public void deleteCamp(@PathVariable String campId) {

  }

}
