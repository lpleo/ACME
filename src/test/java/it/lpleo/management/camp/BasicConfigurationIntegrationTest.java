package it.lpleo.management.camp;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = {Application.class, BasicConfiguration.class})
public class BasicConfigurationIntegrationTest {

  private TestRestTemplate restTemplate;
  private URL base;
  @LocalServerPort
  int port;

  @Before
  public void setUp() throws MalformedURLException {
    restTemplate = new TestRestTemplate("user", "password");
    base = new URL("http://localhost:" + port);
  }

  @Test
  public void whenLoggedUserRequestsHomePage_ThenSuccess()
      throws IllegalStateException, IOException {
    ResponseEntity<String> response
        = restTemplate.getForEntity(base.toString() + "/camp", String.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void whenUserWithWrongCredentials_thenUnauthorizedPage()
      throws Exception {

    restTemplate = new TestRestTemplate("user", "wrongpassword");
    ResponseEntity<String> response
        = restTemplate.getForEntity(base.toString() + "/camp", String.class);

    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
  }
}