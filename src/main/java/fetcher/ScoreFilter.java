package fetcher;


import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ScoreFilter {

    public static <T> Predicate<T> logFiltered(Predicate<T> predicate, Consumer<T> action) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(action);
        return (value) -> {
            if (predicate.test(value)) {
                return true;
            } else {
                action.accept(value);
                return false;
            }
        };
    }

    private ScoreFilter() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}