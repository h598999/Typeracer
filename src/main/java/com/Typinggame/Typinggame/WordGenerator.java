package com.Typinggame.Typinggame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * WordGenerator
 */
public class WordGenerator {

  private List<String> words;
  private Random random;

  public WordGenerator(String filename){
    words = new ArrayList<>();
    random = new Random();
    System.out.println(filename);
    loadWordsFromFile(filename);
  }


  private void loadWordsFromFile(String filename){
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
      String line;
      while ((line = reader.readLine()) != null){
        words.add(line.trim());
      }
    } catch (IOException e ){
      System.err.println("Error reading from file: " + e.getMessage());
    }
  }

  public String getRandomWord(){
    if (words.isEmpty()){
      return null;
    }
    int index = random.nextInt(words.size());
    return words.get(index);
  }

  public List<String> readTenRandomWords(){
    if (words.isEmpty()){
      return null;
    }
    int startIndex;
    if (words.size() == 10){
      startIndex = 0;
    } else {
      startIndex = random.nextInt(words.size()-10);
    }

    List<String> returnWords = new ArrayList<>();

    for (int i = startIndex; i<startIndex+10; i++){
      returnWords.add(words.get(i));
    }
    return returnWords;
  }

  public List<String> getWords(){
    return this.words;
  }

}
