package optional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@FunctionalInterface
interface ExFunction<A, R> {
  R apply(A a) throws Throwable;

  static <A, R> Function<A, Optional<R>> wrap(ExFunction<A, R> op) {
    return a -> {
      try {
        return Optional.of(op.apply(a));
      } catch (Throwable e) {
        System.err.println("Wrapped function threw " + e);
        return Optional.empty();
      }
    };
  }
}

public class PrintFiles {
//
//  public static Optional<Stream<String>> getLinesFromFile(String fn) {
//    try {
//      return Optional.of(Files.lines(Path.of(fn)));
//    } catch (IOException e) {
//      System.err.println(e);
//      return Optional.empty();
//    }
//  }

  public static void main(String[] args) {
    Stream.of("a.txt", "b.txt", "c.txt")
//        .map(PrintFiles::getLinesFromFile)
        .map(ExFunction.wrap(fn -> Files.lines(Path.of(fn))))
        .peek(opt -> {
          if (opt.isEmpty()) {
            System.out.println("ouch, something failed!!!");
          }
        })
        .map(opt -> {
          if (opt.isPresent()) {
            return opt;
          } else {
//            return getLinesFromFile("backup.txt");
            // better to grab this generated function once
            // store in a variable and reuse...
            return ExFunction.wrap((String fn) ->
                Files.lines(Path.of(fn))).apply("backup.txt");
          }
        })
        .filter(opt -> opt.isPresent())
        .flatMap(opt -> opt.get())
        .forEach(System.out::println);
  }
}
