package it.lpleo.management.camp.record;

import it.lpleo.management.camp.record.child.Allergy;
import it.lpleo.management.camp.record.child.Child;
import it.lpleo.management.camp.record.parent.Parent;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  @Autowired
  PersonRepository personRepository;

  public Child getChild(Long childId) {
    return personRepository.getChild(childId);
  }

  public Child getChildByFiscalCode(String fiscalCode) {
    return personRepository.getChildByFiscalCode(fiscalCode);
  }

  public Child insertChild(Child child) {
    return personRepository.insertChild(child);
  }

  public Parent getParent(Long parentId) {
    return personRepository.getParent(parentId);
  }

  public List<Allergy> getAllergiesByChildId(Long childId) {
    return personRepository.getAllergiesByChildId(childId);
  }

  public List<Child> getChildren(Long campYear) {
    return personRepository.getChildren(campYear);
  }

  public Parent getParentByFiscalCode(String fiscalCode) {
    return personRepository.getParentByFiscalCode(fiscalCode);
  }
}
