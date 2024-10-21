package com.Typinggame.Typinggame.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Typinggame.Typinggame.WordGenerator;

/**
 * WordController
 */
@RequestMapping("api/v2/words")
@CrossOrigin
@RestController
public class WordController {
  
  WordGenerator gen = new WordGenerator("./words.txt");

  @GetMapping("/ten")
  public ResponseEntity<List<String>> getTenWords(){
    List<String> words = gen.readTenRandomWords();
    if (words == null || words.isEmpty()){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(words, HttpStatus.OK);
  }
}


