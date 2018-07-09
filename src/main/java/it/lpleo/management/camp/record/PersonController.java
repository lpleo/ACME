package it.lpleo.management.camp.record;

import it.lpleo.management.camp.record.child.Allergy;
import it.lpleo.management.camp.record.child.Child;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Log4j2
@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  PersonService personService;

  @GetMapping("/child/{childId}")
  public Child getChild(@PathVariable Long childId) {
    return personService.getChild(childId);
  }

  @GetMapping("/parent/{parentId}")
  public Child getParent(@PathVariable Long parentId) {
    return personService.getChild(parentId);
  }

  @GetMapping("/child/{childId}/allergy")
  public List<Allergy> getChildAllergies(@PathVariable Long childId) {
    return personService.getAllergiesByChildId(childId);
  }
}
