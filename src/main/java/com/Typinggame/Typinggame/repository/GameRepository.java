package com.Typinggame.Typinggame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Typinggame.Typinggame.models.Game;

/**
 * GameRepository
 */
public interface GameRepository extends JpaRepository<Game, String> {
}
