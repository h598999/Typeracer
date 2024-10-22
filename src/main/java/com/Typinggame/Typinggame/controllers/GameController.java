package com.Typinggame.Typinggame.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Typinggame.Typinggame.models.Game;
import com.Typinggame.Typinggame.service.GameService;

/**
 * GameController
 */
@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired GameService service;

    @PostMapping("/start")
    public ResponseEntity<Game> startGame(){
        Game game = service.StartNewGame();
        return new ResponseEntity<>(game, HttpStatus.OK);      
    }
}
