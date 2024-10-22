package com.Typinggame.Typinggame.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Typinggame.Typinggame.models.Game;
import com.Typinggame.Typinggame.service.GameService;

/**
 * GameController
 */
@RestController
@RequestMapping("/api/game")
@CrossOrigin
public class GameController {

    @Autowired GameService service;

    @PostMapping("/start")
    public ResponseEntity<Game> startGame(){
        Game game = service.StartNewGame();
        return new ResponseEntity<>(game, HttpStatus.OK);      
    }

    @PostMapping("/end/{gameId}")
    public ResponseEntity<Game> endGame(@PathVariable String gameId, @RequestBody Game gameData) {
        Game updatedGame = service.endGame(gameId, gameData);
        if (updatedGame == null) {
            return ResponseEntity.notFound().build(); // Return 404 if game not found
        }
        return ResponseEntity.ok(updatedGame); // Return the updated game
    }
}
