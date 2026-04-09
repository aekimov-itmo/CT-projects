package game;

public class BadPlayer implements Player {
    @Override
    public Move move(Position position, Cell cell) {
        throw new RuntimeException();
    }
}
