package com.example.DW_backend.controller.neo4j;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.neo4j.driver.Values.parameters;

@RestController
@RequestMapping("/neo4j")
public class MovieController {

    private final Driver driver;

    public MovieController(Driver driver) {
        this.driver = driver;
    }
    @GetMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)// 测试用的
    public List<String> getMovieTitles() {
        try (Session session = driver.session()) {
            long startTime = System.currentTimeMillis();
            List<String> result = session.run("MATCH (n:Movie) RETURN n ")
                    .list(r -> r.get("n").asNode().get("name").asString());
            long endTime = System.currentTimeMillis();
            long queryTime = endTime - startTime;
            System.out.println("Query获取全部电影信息 time: " + queryTime + " ms");
            return result;
        }
    }

    // 查询某个导演执行的所有电影
    @GetMapping(path = "/movies/{directorName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getMoviesByDirector(@PathVariable String directorName) {
        try (Session session = driver.session()) {
            long startTime = System.currentTimeMillis();
            List<Map<String, Object>> result = session.run("MATCH (d:Director {name: $directorName})-[:DIRECTED]->(m:Movie) RETURN m",
                            parameters("directorName", directorName))
                    .list(r -> r.get("m").asNode().asMap());
            long endTime = System.currentTimeMillis();
            long queryTime = endTime - startTime;
            System.out.println("Query获取导演电影 time: " + queryTime + " ms");
            return result;
        }
    }

    // 查询某个演员参演的所有电影
    @GetMapping(path = "/actorMovies/{actorName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getMoviesByActor(@PathVariable String actorName) {
        try (Session session = driver.session()) {
            long startTime = System.currentTimeMillis();
            List<Map<String, Object>> result = session.run("MATCH (a:Actor {name: $actorName})-[:ACTED_IN]->(m:Movie) RETURN m",
                            parameters("actorName", actorName))
                    .list(r -> r.get("m").asNode().asMap());
            long endTime = System.currentTimeMillis();
            long queryTime = endTime - startTime;
            System.out.println("Query获取演员电影 time: " + queryTime + " ms");
            return result;
        }
    }



}

