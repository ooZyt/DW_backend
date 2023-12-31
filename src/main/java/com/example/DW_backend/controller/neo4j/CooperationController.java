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
public class CooperationController {

    private final Driver driver;

    // 根据次数查询合作次数超过n次的演员组合
    public CooperationController(Driver driver) {
        this.driver = driver;
    }
    @GetMapping(path = "/cooperations/actor/{times}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getCooperations(@PathVariable int times) {
        try (Session session = driver.session()) {
            long startTime = System.currentTimeMillis();
            List<Map<String, Object>> result =
                    session.run("MATCH (a1:Actor)-[r:COOPERATED]->(a2:Actor) WHERE r.times > $times RETURN a1.name AS Actor1, a2.name AS Actor2, r.times AS CooperationTimes",
                                    parameters("times", times))
                            .list(r -> r.asMap());
            long endTime = System.currentTimeMillis();
            long queryTime = endTime - startTime;
            System.out.println("Query根据次数查询合作次数超过n次的演员组合 time: " + queryTime + " ms");
            return result;
        }
    }


    // 根据次数查询合作次数超过n次的演员导演组合
    @GetMapping(path = "/cooperations/director/{times}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getCooperationsWithDirector(@PathVariable int times) {
        try (Session session = driver.session()) {
            long startTime = System.currentTimeMillis();
            List<Map<String, Object>> result =
                    session.run("MATCH (a:Actor)-[r:COOPERATED_WITH]->(d:Director) WHERE r.times > $times RETURN a.name AS Actor, d.name AS Director, r.times AS CooperationTimes",
                                    parameters("times", times))
                            .list(r -> r.asMap());
            long endTime = System.currentTimeMillis();
            long queryTime = endTime - startTime;
            System.out.println("Query根据次数查询合作次数超过n次的演员和导演组合 time: " + queryTime + " ms");
            return result;
        }
    }


    // 输入演员名称和基准次数，查询和该演员合作超过n次的演员
    @GetMapping(path = "/cooperations/actor/{actorName}/{times}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getCooperationsWithActor(@PathVariable String actorName, @PathVariable int times) {
        try (Session session = driver.session()) {
            long startTime = System.currentTimeMillis();
            List<Map<String, Object>> result = session.run("MATCH (a1:Actor {name: $actorName})-[r:COOPERATED]->(a2:Actor) WHERE r.times > $times RETURN a1.name AS Actor1, a2.name AS Actor2, r.times AS CooperationTimes",
                            parameters("actorName", actorName, "times", times))
                    .list(r -> r.asMap());
            long endTime = System.currentTimeMillis();
            long queryTime = endTime - startTime;
            System.out.println("Query根据次数和演员名称查询合作次数超过n次的某演员组合 time: " + queryTime + " ms");
            return result;
        }
    }

    // 查询演员合作关系，合作次数由高到低的前50名
    @GetMapping(path = "/cooperations/top/actor", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getTopActorCooperations() {
        try (Session session = driver.session()) {
            long startTime = System.currentTimeMillis();
            List<Map<String, Object>> result = session.run("MATCH (a1:Actor)-[r:COOPERATED]->(a2:Actor) RETURN a1.name AS Actor1, a2.name AS Actor2, r.times AS CooperationTimes ORDER BY r.times DESC LIMIT 50")
                    .list(r -> r.asMap());
            long endTime = System.currentTimeMillis();
            long queryTime = endTime - startTime;
            System.out.println("Query查询演员合作关系，合作次数由高到低的前50名 time: " + queryTime + " ms");
            return result;
        }

    }
    @GetMapping(path = "/cooperations/top/director-actor", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getTopDirectorActorCooperations() {
        try (Session session = driver.session()) {
            long startTime = System.currentTimeMillis();
            List<Map<String, Object>> result = session.run("MATCH (a:Actor)-[r:COOPERATED_WITH]->(d:Director) RETURN a.name AS Actor, d.name AS Director, r.times AS CooperationTimes ORDER BY r.times DESC LIMIT 50")
                    .list(r -> r.asMap());
            long endTime = System.currentTimeMillis();
            long queryTime = endTime - startTime;
            System.out.println("Query查询导演和演员合作关系，合作次数由高到低的前50名 time: " + queryTime + " ms");
            return result;
        }
    }


}
