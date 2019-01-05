package it.lpleo.management.camp.record;

import it.lpleo.management.camp.record.child.Allergy;
import it.lpleo.management.camp.record.child.Child;
import it.lpleo.management.camp.record.child.Subscription;
import it.lpleo.management.camp.record.parent.Parent;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Log4j2
@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  PersonService personService;

  @GetMapping("/children")
  public List<Child> getChildren(@PathVariable(required = false) Long campYear) {
    log.info("get filtered children");
    return personService.getChildren(campYear);
  }

  @GetMapping("/child/{childId}")
  public Child getChild(@PathVariable Long childId) {
    log.info("get child with id [" + childId + "]");
    return personService.getChild(childId);
  }

  @GetMapping("/child")
  public Child getChildByFiscalCode(@RequestParam String fiscalCode) {
    log.info("get child with fiscalCode [" + fiscalCode + "]");
    return personService.getChildByFiscalCode(fiscalCode);
  }

  @PostMapping("/child")
  public Child insertChild(@RequestBody Child child) {
    log.info("insert child");
    return personService.insertChild(child);
  }

  @GetMapping("/parent/{parentId}")
  public Child getParent(@PathVariable Long parentId) {
    log.info("get parent with id [" + parentId + "]");
    return personService.getChild(parentId);
  }

  @GetMapping("/parent")
  public Parent getParentByFiscalCode(@RequestParam String fiscalCode) {
    log.info("get parent with fiscal code [" + fiscalCode + "]");
    return personService.getParentByFiscalCode(fiscalCode);
  }

  @GetMapping("/child/{childId}/allergy")
  public List<Allergy> getChildAllergies(@PathVariable Long childId) {
    log.info("get allergies for id [" + childId + "]");
    return personService.getAllergiesByChildId(childId);
  }

  @GetMapping("/child/{childId}/subscription")
  public List<Subscription> getChildSubscriptions(@PathVariable Long childId,
      @RequestParam Long campId) {
    log.info("get subscriptions for child id [" + childId + "] and camp id [" + campId + "]");
    return personService.getSubscriptions(campId, childId);
  }
}
