package com.example.DW_backend.mysql.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ActorPairDTO {
    private String actor1;
    private String actor2;
    private int movieCount;

    // Constructors, getters, and setters

    public ActorPairDTO() {
    }

    public ActorPairDTO(String actor1, String actor2, int movieCount) {
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.movieCount = movieCount;
    }

    // Getters and Setters
    public String getActor1() {
        return actor1;
    }

    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }

    public String getActor2() {
        return actor2;
    }

    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }

    public int getMovieCount() {
        return movieCount;
    }

    public void setMovieCount(int movieCount) {
        this.movieCount = movieCount;
    }
}
