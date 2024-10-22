package com.Typinggame.Typinggame.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Typinggame.Typinggame.WordGenerator;
import com.Typinggame.Typinggame.models.Game;
import com.Typinggame.Typinggame.repository.GameRepository;

/**
 * GameService
 */
@Service
public class GameService {

    @Autowired GameRepository repo;
    WordGenerator gen = new WordGenerator("./words.txt");

    public Game StartNewGame(){
        Game game = new Game();
        game.setId(UUID.randomUUID().toString());
        game.setStartTime(LocalDateTime.now());
        return repo.save(game);
    }

    public Game endGame(String gameId, Game gameData){
        Optional<Game> game = repo.findById(gameId);
        if (game.isEmpty()){
            return null;
        }
        Game existingGame = game.get();
        existingGame.setEndTime(LocalDateTime.now());
        existingGame.setWPM(gameData.getWPM());
        return repo.save(game.get());
    }
    
}
