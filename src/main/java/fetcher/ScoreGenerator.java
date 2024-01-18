package fetcher;


public class ScoreGenerator<T> {
    private final double score;
    private final T value;

    public double getScore() {
        return this.score;
    }

    public T getValue() {
        return this.value;
    }

    public ScoreGenerator(double score, T value) {
        this.score = score;
        this.value = value;
    }

    public String toString() {
        return "ScoreGenerator(score=" + this.getScore() + ", value=" + this.getValue() + ")";
    }
}
