package expression;

public class Divide extends AbstractExpression {

    public Divide(ExpressionType first, ExpressionType second) {
        super(first, second);
    }

    @Override
    public int operate(int first, int second) {
        return first / second;
    }

    @Override
    public String getSymbol() {
        return "/";
    }
}
