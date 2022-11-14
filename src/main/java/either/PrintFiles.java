package either;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Stream;

public class PrintFiles {
  public static void main(String[] args) {
    Map<String, String> recoveries = Map.of("b.txt", "backup.txt");

      Stream.of("a.txt", "b.txt", "c.txt")
          .map(ExFunction.wrap(fn -> Files.lines(Path.of(fn))))
//          .peek(opt -> {
//            if (opt.isFailure()) {
//              System.out.println("ouch, something failed!!!");
//            }
//          })
          .map(e -> e.useFailure(System.out::println))
//          .map(opt -> {
//            if (opt.isSuccess()) {
//              return opt;
//            } else {
//              String badFile = opt.getFailure().getMessage();
//              System.out.println("Problem opening " + badFile);
//              return ExFunction.wrap((String fn) ->
//                  Files.lines(Path.of(fn))).apply("backup.txt");
//            }
//          })

          .map(e -> e.recover(
              ExFunction.wrap(
                  t -> Files.lines(Path.of(
                      recoveries.get(t.getMessage()))))))
          .filter(opt -> opt.isSuccess())
          .flatMap(opt -> opt.getSuccess())
          .forEach(System.out::println);
  }
}
