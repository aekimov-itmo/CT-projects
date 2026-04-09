package expression;

import java.util.List;
import java.util.Objects;

public class Variable implements ExpressionType {
    private String var;
    private int index = -1;

    public Variable(String var) {
        this.var = var;
    }

    public Variable(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        if (index != -1) {
            return "$" + index;
        }
        return var;
    }

    public int evaluate(int var) {
        return var;
    }

    public int evaluate(int fVar, int sVar, int tVar) {
        switch (var) {
            case "x" -> {
                return fVar;
            }
            case "y" -> {
                return sVar;
            }
            case "z" -> {
                return tVar;
            }
            default -> throw new IllegalStateException("Unexpected value: " + var);
        }
    }

    public int evaluate(List<Integer> list) {
        return list.get(index);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Variable exp = (Variable) obj;
        return Objects.equals(var, exp.var) && index == exp.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(var, index);
    }
}
