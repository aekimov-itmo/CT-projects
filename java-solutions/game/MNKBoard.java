package game;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final int m;
    private final int n;
    private final int k;
    public static boolean bonus;
    private int drawCounter;


    public MNKBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.drawCounter = m * n;
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;

    }
    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        drawCounter -= 1;
        int row = countLine(move.getRow(), move.getColumn(), 0, 1, move.getValue());
        int column = countLine(move.getRow(), move.getColumn(), 1, 0, move.getValue());
        int mainDiagonal = countLine(move.getRow(), move.getColumn(), 1, 1, move.getValue());
        int secondaryDiagonal = countLine(move.getRow(), move.getColumn(), 1, -1, move.getValue());

        if (checkBoard(k, row, column, mainDiagonal, secondaryDiagonal)) {
            return Result.WIN;
        } else if (drawCounter == 0) {
            return Result.DRAW;
        } else if (checkBoard(4, row, column, mainDiagonal, secondaryDiagonal) && !bonus) {
            bonus = true;
            return Result.UNKNOWN;
        } else {
            bonus = false;
            turn = turn == Cell.X ? Cell.O : Cell.X;
            return Result.UNKNOWN;
        }
    }

    public static boolean getBonus() {
        return bonus;
    }

    private int countLine(int row, int column, int rowStep, int columnStep, Cell turn) {
        int counter = 1;
        int rowArrow = row + rowStep;
        int columnArrow = column + columnStep;

        while (columnArrow > -1  && rowArrow < m && columnArrow < n && cells[rowArrow][columnArrow] == turn) { // посмотри если будет вылетать индекс то -2
            counter += 1;
            rowArrow += rowStep;
            columnArrow += columnStep;
        }
        //System.err.println("RowArrow: " + rowArrow + " ColumnArrow " + columnArrow);
        rowArrow = row - rowStep;
        columnArrow = column - columnStep;

        while (columnArrow < n && rowArrow > -1 && columnArrow > -1 && cells[rowArrow][columnArrow] == turn) { // посмотри если будет вылетать индекс то -2
            counter += 1;
            rowArrow -= rowStep;
            columnArrow -= columnStep;
        }
//        System.err.println("RowArrow: " + rowArrow + " ColumnArrow " + columnArrow);
//        System.err.println(counter);
        return counter;
    }

    private boolean checkBoard(int checkValue, int row, int column, int diag1, int diag2) {
        if (row >= checkValue || column >= checkValue || diag1 >= checkValue|| diag2 >= checkValue) {
            return true;
        }
        return false;
    }


    @Override
    public boolean isValid(final Move move) { // Изменить проверку
        return 0 <= move.getRow() && move.getRow() <= n - 1
                && 0 <= move.getColumn() && move.getColumn() <= m - 1
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() { // ВЫВОД БРОСАЕТ ИСКЛЮЧЕНИЯ
        final StringBuilder sb = new StringBuilder("   ");
        for (int i = 0; i < n; i++) {
            sb.append(String.format("%3d", i));
        }
        for (int r = 0; r < m; r++) {
            sb.append(System.lineSeparator());
            sb.append(String.format("%3d", r));
            for (int c = 0; c < n; c++) {
                sb.append(String.format("%3s", SYMBOLS.get(cells[r][c])));
            }
        }
        return sb.toString();
    }


}
