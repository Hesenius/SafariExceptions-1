package closure;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class Adding {
  public static int add(int a, int b) {
    return a + b;
  }

  public static void main(String[] args) {
    Function<IntBinaryOperator, IntUnaryOperator> makeNewFunction  =
        (IntBinaryOperator bo) -> {
          return x -> bo.applyAsInt(x, 3);
        };

    IntUnaryOperator addThree = makeNewFunction.apply(Adding::add);

    System.out.println(addThree.applyAsInt(99));
  }
}
