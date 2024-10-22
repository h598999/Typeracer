package com.Typinggame.Typinggame.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

/**
 * Game
 */
@Entity
public class Game {

    @Id
    private String id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int WPM;

    @ManyToMany(mappedBy = "gamesPlayed")
    private List<User> players;

    public Game(){}

    public Game(String id, LocalDateTime startTime, LocalDateTime endTime){
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.players = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public int getWPM() {
        return WPM;
    }

    public void setWPM(int wPM) {
        WPM = wPM;
    }
}
