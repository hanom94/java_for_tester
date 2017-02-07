package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hanom on 06.02.2017.
 */
public class Collections {

  public static void main(String[] args) {
    String[] langs ={"Java", "C#", "Python", "PHP"};

    /*List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");
    languages.add("PHP");*/

    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

    for (String l : langs){ // Указатель на нужный элемент массива
      System.out.println("Я хочу выучить " + l);
    }
  }
}
