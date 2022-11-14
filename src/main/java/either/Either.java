package either;

import java.util.function.Consumer;
import java.util.function.Function;

@FunctionalInterface
interface ExFunction<A, R> {
  R apply(A a) throws Throwable;

  static <A, R> Function<A, Either<R>> wrap(ExFunction<A, R> op) {
    return a -> {
      try {
        return Either.sucess(op.apply(a));
      } catch (Throwable e) {
        return Either.failure(e);
      }
    };
  }
}

public class Either<S> {
  private S success;
  private Throwable failure;
  private Either(S s, Throwable t) {
    this.success = s;
    this.failure = t;
  }
  public static <S> Either<S> sucess(S s) {
    return new Either<>(s, null);
  }
  public static <S> Either<S> failure(Throwable f) {
    return new Either<>(null, f);
  }
  public boolean isSuccess() {
    return failure == null;
  }
  public boolean isFailure() {
    return failure != null;
  }
  public S getSuccess() {
    if (isFailure()) throw new IllegalStateException(
        "Attempt to get success from failure");
    return success;
  }
  public <S1> Either<S1> mapSuccess(Function<S, S1> op) {
    if (isSuccess()) {
      return Either.sucess(op.apply(success));
    } else {
      return (Either<S1>) this;
    }
  }
  public Throwable getFailure() {
    if (isSuccess()) throw new IllegalStateException(
        "Attempt to get failure from success");
    return failure;
  }
  public Either<S> useFailure(Consumer<Throwable> op) {
    if (isFailure()) {
      op.accept(failure);
    }
    return this;
  }
  public Either<S> recover(Function<Throwable, Either<S>> op) {
    if (isFailure()) {
      return op.apply(failure);
    }
    return this;
  }
}
