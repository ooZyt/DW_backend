package com.example.DW_backend.mysql.pojo;
import lombok.Data;

@Data
public class ActorDirectorPairDTO {
    private String actorName;
    private String directorName;
    private int movieCount;

    // Constructors
    public ActorDirectorPairDTO() {
    }

    public ActorDirectorPairDTO(String actorName, String directorName, int movieCount) {
        this.actorName = actorName;
        this.directorName = directorName;
        this.movieCount = movieCount;
    }

    // Getters and Setters
    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public int getMovieCount() {
        return movieCount;
    }

    public void setMovieCount(int movieCount) {
        this.movieCount = movieCount;
    }
}
