package guru.springframework;

import java.util.Objects;

public class Pair {

    private final  String  from;
    private final  String  to;

     Pair(String from, String to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Implements hashCode and equals so that they can be compared correctly
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(from, pair.from)
                && Objects.equals(to, pair.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
