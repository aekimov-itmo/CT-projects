package game;

public class SwedishTournament {

    private final Player[] gamers;
    private final int m;
    private final int n;
    private final int k;
    int[] table;

    public SwedishTournament(Player[] gamers, int m, int n, int k) {
        this.gamers = gamers;
        this.m = m;
        this.n = n;
        this.k = k;
        table = new int[gamers.length];
    }

    public static int[] fillTable(int[] table, Player[] gamers, int m, int n, int k) {
        try {
            for (int i = 0; i < gamers.length; i++) {
                for (int j = i + 1; j < gamers.length; j++) {
                    System.out.println("Player" + i + " vs " + "Player" + j);
                    Game game1 = new Game(false, gamers[i], gamers[j]);
                    int result1;

                    result1 = game1.play(new MNKBoard(m, n, k));
                    switch (result1) {
                        case 1 -> table[i] += 3;
                        case 0 -> {
                            table[i] += 1;
                            table[j] += 1;
                        }
                        case 2 -> table[j] += 3;
                    }
                    System.out.println("Game1 result: " + result1);


                    Game game2 = new Game(false, gamers[j], gamers[i]);
                    int result2;

                    result2 = game2.play(new MNKBoard(m, n, k));
                    switch (result2) {
                        case 1 -> table[j] += 3;
                        case 0 -> {
                            table[i] += 1;
                            table[j] += 1;
                        }
                        case 2 -> table[i] += 3;
                    }
                    System.out.println("Game2 result: " + result2);
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return table;

    }

    public static void drawTable(int[] table) {
        int maxScore = 0;
        int maxScoreIndex = 0;
        System.out.println();
        System.out.println("Player   " + "Score");
        for (int i = 0; i < table.length; i++) {
            if (maxScore < table[i]) {
                maxScore = table[i];
                maxScoreIndex = i;
            }
            System.out.println("Player" + i + "   " + "Score " + table[i]);
        }

        System.out.println();
        System.out.println("Player" + maxScoreIndex + " is winner with score: " + maxScore);
    }
}
