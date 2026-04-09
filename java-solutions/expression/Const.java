package expression;

import java.util.List;
import java.util.Objects;

public class Const implements ExpressionType {
    private final int constant;
    public Const(int constant) {
        this.constant = constant;
    }

    @Override
    public String toString() {
        return Integer.toString(constant);
    }

    public int evaluate(int var) {
        return constant;
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return constant;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return constant;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Const exp = (Const) obj;
        return Objects.equals(constant, exp.constant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(constant);
    }
}
