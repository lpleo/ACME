package it.lpleo.management.camp.record;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
