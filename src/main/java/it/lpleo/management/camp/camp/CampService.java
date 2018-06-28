package it.lpleo.management.camp.camp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CampService {

  @Autowired
  private CampRepository campRepository;

  public List<Camp> getAllCamps() {
    return campRepository.retrieveAllCamps();
  }
}
