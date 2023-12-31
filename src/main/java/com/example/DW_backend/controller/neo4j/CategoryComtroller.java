package com.example.DW_backend.controller.neo4j;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.neo4j.driver.Values.parameters;


@CrossOrigin(origins = {"http://localhost:5173","http://localhost:8080"})
@RestController
@RequestMapping("/neo4j")
public class CategoryComtroller {

    private final Driver driver;

    public CategoryComtroller(Driver driver) {
        this.driver = driver;
    }

    @GetMapping(path = "/categoryCount/{categoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getMovieCountByCategory(@PathVariable String categoryName) {
        try (Session session = driver.session()) {
            long startTime = System.currentTimeMillis();
            Map<String, Object> result = session.run("MATCH (m:Movie)-[:BELONGS_TO]->(c:Category {categoryName: $categoryName}) RETURN c.categoryName AS Category, count(m) AS MovieCount",
                            parameters("categoryName", categoryName))
                    .single().asMap();

            long endTime = System.currentTimeMillis();
            long queryTime = endTime - startTime;
            System.out.println("Query某个类别的所有电影数量 time: " + queryTime + " ms");
            return result;

        }
    }

    @GetMapping(path = "/categoryMovies/{categoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getMoviesByCategory(@PathVariable String categoryName) {
        try (Session session = driver.session()) {
            long startTime = System.currentTimeMillis();
            List<Map<String, Object>> result = session.run("MATCH (m:Movie)-[:BELONGS_TO]->(c:Category {categoryName: $categoryName}) RETURN m",
                            parameters("categoryName", categoryName))
                    .list(r -> r.get("m").asNode().asMap());
            long endTime = System.currentTimeMillis();
            long queryTime = endTime - startTime;
            System.out.println("Query某个类别的所有电影信息 time: " + queryTime + " ms");
            return result;
        }
    }

    @GetMapping(path = "/movies/topCooperations/{categoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getTopCooperationsInCategoryMovies(@PathVariable String categoryName) {
        try (Session session = driver.session()) {
            long startTime = System.currentTimeMillis();
            List<Map<String, Object>>  result =
                    session.run(
                                    "MATCH (m:Movie)-[:BELONGS_TO]->(c:Category {categoryName: $categoryName}), (a1:Actor)-[:ACTED_IN]->(m)<-[:ACTED_IN]-(a2:Actor) " +
                                            "WHERE id(a1) < id(a2) " +
                                            "RETURN a1.name AS Actor1, a2.name AS Actor2, sum(m.commentCount) AS TotalComments " +
                                            "ORDER BY TotalComments DESC " +
                                            "LIMIT 10",
                                    parameters("categoryName", categoryName))
                            .list(r -> r.asMap());
            long endTime = System.currentTimeMillis();
            long queryTime = endTime - startTime;
            System.out.println("Query某个类别最受关注的演员组合 time: " + queryTime + " ms");
            return result;
        }
    }
}
