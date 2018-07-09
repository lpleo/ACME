package it.lpleo.management.camp.record;

import it.lpleo.management.camp.record.child.Allergy;
import it.lpleo.management.camp.record.child.AllergyRowMapper;
import it.lpleo.management.camp.record.child.Child;
import it.lpleo.management.camp.record.child.ChildRowMapper;
import it.lpleo.management.camp.record.parent.Parent;
import it.lpleo.management.camp.record.parent.ParentRowMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

  private static final String SELECT_SINGLE_CHILD = "SELECT * FROM CHILD WHERE id = ?";
  private static final String SELECT_SINGLE_PARENT = "SELECT * FROM PARENT WHERE id = ?";
  private static final String SELECT_PARENT_IDS_FROM_CHILD_ID = "SELECT parentId FROM PARENT_CHILD WHERE childId = ?";
  private static final String SELECT_ALLERGIES_BY_CHILD_ID = "SELECT * FROM ALLERGY WHERE childId = ?";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private ChildRowMapper childRowMapper;

  @Autowired
  private ParentRowMapper parentRowMapper;

  @Autowired
  private AllergyRowMapper allergyRowMapper;

  public Child getChild(Long childId) {
    return jdbcTemplate
        .queryForObject(SELECT_SINGLE_CHILD, new Object[]{childId}, childRowMapper);
  }

  public List<Long> getParentIdsFromChild(Long childId) {
    return jdbcTemplate.queryForList(SELECT_PARENT_IDS_FROM_CHILD_ID, Long.class);
  }

  public Parent getParent(Long parentId) {
    return jdbcTemplate
        .queryForObject(SELECT_SINGLE_PARENT, new Object[]{parentId}, parentRowMapper);
  }

  public List<Allergy> getAllergiesByChildId(Long childId) {
    return jdbcTemplate
        .query(SELECT_ALLERGIES_BY_CHILD_ID, new Object[]{childId}, allergyRowMapper);
  }
}
