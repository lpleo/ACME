package it.lpleo.management.camp.record;

import it.lpleo.management.camp.record.child.Allergy;
import it.lpleo.management.camp.record.child.AllergyRowMapper;
import it.lpleo.management.camp.record.child.Child;
import it.lpleo.management.camp.record.child.ChildRowMapper;
import it.lpleo.management.camp.record.child.Subscription;
import it.lpleo.management.camp.record.child.SubscriptionRowMapper;
import it.lpleo.management.camp.record.parent.Parent;
import it.lpleo.management.camp.record.parent.ParentRowMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

  private static final String SELECT_SINGLE_CHILD = "SELECT * FROM CHILD WHERE id = :id";
  private static final String SELECT_SINGLE_CHILD_BY_FISCALCODE = "SELECT * FROM CHILD WHERE FISCALCODE = :fiscalCode";
  private static final String INSERT_CHILD = "INSERT INTO CHILD(fiscalCode, name, surname, birthDate) values (:fiscalCode,:name,:surname,:birthDate)";
  private static final String INSERT_PARENT =
      "INSERT INTO PARENT(fiscalCode, name, surname, email, telephoneNumber) "
          + "values (:fiscalCode,:name,:surname, :email, :phoneNumber)";
  private static final String SELECT_SINGLE_PARENT = "SELECT * FROM PARENT WHERE id = :id";
  private static final String SELECT_SINGLE_PARENT_BY_FISCALCODE = "SELECT * FROM PARENT WHERE FISCALCODE = :fiscalCode";
  private static final String SELECT_PARENT_IDS_FROM_CHILD_ID = "SELECT parentId FROM PARENT_CHILD WHERE childId = :childId";
  private static final String SELECT_ALLERGIES_BY_CHILD_ID = "SELECT * FROM ALLERGY WHERE childId = :childId";
  private static final String SELECT_SUBSCRIPTION_BY_CHILD_ID = "SELECT * FROM SUBSCRIPTION WHERE childId = :childId";
  private static final String SELECT_CHILDREN_WITH_FILTER = "SELECT CH.* FROM CHILD CH "
      + "LEFT JOIN SUBSCRIPTION SC ON (CH.id = SC.childId) "
      + "LEFT JOIN CAMP CM ON (CM.id = SC.campId) "
      + "WHERE 1=1";

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Autowired
  private ChildRowMapper childRowMapper;

  @Autowired
  private ParentRowMapper parentRowMapper;

  @Autowired
  private AllergyRowMapper allergyRowMapper;

  @Autowired
  private SubscriptionRowMapper subscriptionRowMapper;

  public Child getChild(Long childId) {
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("id", childId);
    return jdbcTemplate
        .queryForObject(SELECT_SINGLE_CHILD, parameters, childRowMapper);
  }

  public Child getChildByFiscalCode(String fiscalCode) {
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("fiscalCode", fiscalCode);
    return jdbcTemplate
        .queryForObject(SELECT_SINGLE_CHILD_BY_FISCALCODE, parameters, childRowMapper);
  }

  public Child insertChild(Child child) {
    MapSqlParameterSource parameters = getSqlParameterSourceFromPreson(child);
    parameters.addValue("birthDate", child.getBirthDate());
    jdbcTemplate.update(INSERT_CHILD, parameters);
    return getChildByFiscalCode(child.getFiscalCode());
  }

  public Parent insertParent(Parent parent) {
    MapSqlParameterSource parameters = getSqlParameterSourceFromPreson(parent);
    parameters.addValue("email", parent.getEmail());
    parameters.addValue("phoneNumber", parent.getTelephoneNumber());
    jdbcTemplate.update(INSERT_PARENT, parameters);
    return getParentByFiscalCode(parent.getFiscalCode());
  }

  public List<Long> getParentIdsFromChild(Long childId) {
    MapSqlParameterSource parameter = new MapSqlParameterSource();
    parameter.addValue("childId", childId);
    return jdbcTemplate.queryForList(SELECT_PARENT_IDS_FROM_CHILD_ID, parameter, Long.class);
  }

  public Parent getParent(Long parentId) {
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("id", parentId);
    return jdbcTemplate
        .queryForObject(SELECT_SINGLE_PARENT, parameters, parentRowMapper);
  }

  public Parent getParentByFiscalCode(String fiscalCode) {
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("fiscalCode", fiscalCode);
    return jdbcTemplate
        .queryForObject(SELECT_SINGLE_PARENT_BY_FISCALCODE, parameters, parentRowMapper);
  }

  public List<Allergy> getAllergiesByChildId(Long childId) {
    MapSqlParameterSource parameter = new MapSqlParameterSource();
    parameter.addValue("childId", childId);
    return jdbcTemplate
        .query(SELECT_ALLERGIES_BY_CHILD_ID, parameter, allergyRowMapper);
  }

  public List<Child> getChildren(Long campYear) {

    String completeQuery = SELECT_CHILDREN_WITH_FILTER;
    MapSqlParameterSource parameter = new MapSqlParameterSource();

    if (campYear != null) {
      completeQuery += " AND YEAR = :year";
      parameter.addValue("year", campYear);
    }

    return jdbcTemplate.query(completeQuery, parameter, childRowMapper);
  }

  public List<Subscription> getSubscriptions(Long campId, Long childId) {

    String completeQuery = SELECT_SUBSCRIPTION_BY_CHILD_ID;

    MapSqlParameterSource parameter = new MapSqlParameterSource();
    parameter.addValue("childId", childId);

    if (campId != null) {
      completeQuery += " AND campId = :campId";
      parameter.addValue("campId", campId);
    }

    return jdbcTemplate
        .query(completeQuery, parameter, subscriptionRowMapper);
  }

  private MapSqlParameterSource getSqlParameterSourceFromPreson(Person person) {
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("fiscalCode", person.getFiscalCode());
    parameters.addValue("name", person.getName());
    parameters.addValue("surname", person.getSurname());
    return parameters;
  }

}
