package org.example;

sealed interface X permits Main {
}

public final class Main implements X {
  public static void main(String[] args) {
    System.out.println("Hello Java 17 World!");
  }
}
