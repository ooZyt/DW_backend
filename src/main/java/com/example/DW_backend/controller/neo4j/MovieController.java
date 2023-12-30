package com.example.DW_backend.controller.neo4j;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class MovieController {

    private final Driver driver;

    public MovieController(Driver driver) {
        this.driver = driver;
    }
    @GetMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMovieTitles() {

        try (Session session = driver.session()) {
            return session.run("MATCH (n:Movie) RETURN n LIMIT 25")
                    .list(r -> r.get("n").asNode().get("name").asString());
        }

    }

}

