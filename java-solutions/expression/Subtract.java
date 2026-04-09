package expression;

public class Subtract extends AbstractExpression {
    public Subtract(ExpressionType first, ExpressionType second) {
        super(first, second);
    }

    @Override
    public int operate(int first, int second) {
        return first - second;
    }

    @Override
    public String getSymbol() {
        return "-";
    }
}
