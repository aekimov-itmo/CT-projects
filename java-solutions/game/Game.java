package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game {
    private final boolean log;
    private final Player player1, player2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(Board board) {
        int currentPl = 1;
        while (true) {
            int result;
            if (currentPl == 1) {
                result = move(board, player1, 1);
            } else {
                result = move(board, player2, 2);
            }
            if (result != -1) {
                return result;
            }
            if (!MNKBoard.getBonus()) {
                currentPl = 3 - currentPl;
            }









//

//            if (!mnkBoard.getBonus()) {
//
//
//
//            }

        }
    }

    private int move(final Board board, final Player player, final int no) {
        try {
            final Move move = player.move(board.getPosition(), board.getCell());
            final Result result = board.makeMove(move);
            log("Player " + no + " move: " + move);
            log("Position:" + System.lineSeparator() + board);
            if (result == Result.WIN) {
                log("Player " + no + " won");
                return no;
            } else if (result == Result.LOSE) {
                log("Player " + no + " lose");
                return 3 - no;
            } else if (result == Result.DRAW) {
                log("Draw");
                return 0;
            } else {
                return -1;
            }
        } catch (RuntimeException e) {
            System.out.println("Player throws Exception and lose the game");
            return 3 - no;
        }

    }

    private void log (final String message) {
        if (log) {
            System.out.println(message);
        }
    }

}
