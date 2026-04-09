package expression;

import java.util.List;
import java.util.Objects;

public abstract class AbstractExpression implements ExpressionType {
    protected ExpressionType first;
    protected ExpressionType second;

    public AbstractExpression(ExpressionType first, ExpressionType second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(first.toString()).append(" ").append(getSymbol()).append(" ").append(second.toString()).append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AbstractExpression exp = (AbstractExpression) obj;
        return Objects.equals(first, exp.first) && Objects.equals(second, exp.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), first, second, getSymbol());
    }

    @Override
    public int evaluate(int var) {
        return evaluate(var, 0, 0);
    }

    @Override
    public int evaluate(int fVar, int sVar, int tVar) {
        return operate(first.evaluate(fVar, sVar, tVar), second.evaluate(fVar, sVar, tVar));
    }

    @Override
    public int evaluate(List<Integer> list) {
        return operate(first.evaluate(list), second.evaluate(list));
    }

    protected abstract int operate(int first, int second);
    public abstract String getSymbol();
}
