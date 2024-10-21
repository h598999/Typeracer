package com.Typinggame;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.Typinggame.Typinggame.WordGenerator;

/**
 * WordGeneratorTest
 */
public class WordGeneratorTest {

  private WordGenerator gen = new WordGenerator("words-test.txt");

  @Test
  public void getRandomWord(){
    String retrieved = gen.getRandomWord();
    assertTrue(retrieved.equals("Hello") || retrieved.equals("World"));
  }

  @Test
  public void getTenWordsTest(){
    System.out.println(gen.getWords());;
    List<String> retrievedWords = gen.readTenRandomWords();
    for (int i = 0; i<10; i++){
      if (i%2 == 0){
        assertTrue(retrievedWords.get(i).equals("Hello"));
      } else {
        assertTrue(retrievedWords.get(i).equals("World"));
      }
    }
    assertTrue(retrievedWords.size() == 10);
  }
}
