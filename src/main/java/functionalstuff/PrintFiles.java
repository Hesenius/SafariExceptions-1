package functionalstuff;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class PrintFiles {

  public static Stream<String> getLinesFromFile(String fn) {
    try {
      return Files.lines(Path.of(fn));
    } catch (IOException e) {
//      throw new RuntimeException(e);
      System.err.println(e);
      return Stream.empty();
    }
  }

  public static void main(String[] args) {
    Stream.of("a.txt", "b.txt", "c.txt")
        .flatMap(PrintFiles::getLinesFromFile)
        .forEach(System.out::println);
  }
}
