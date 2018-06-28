package it.lpleo.management.camp.camp;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Log4j2
@RestController
@RequestMapping("/camp")
public class CampController {

  @Autowired
  private CampService campService;

  @PostMapping
  public void insertNewCamp(@RequestBody Camp camp) {

  }

  @GetMapping
  public List<Camp> getAllCamps() {
    log.info("get all camps");
    return campService.getAllCamps();
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
