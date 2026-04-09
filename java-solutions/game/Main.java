package game;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {

//    private  void rusLog(String message, boolean rusLog) {
//        if (rusLog) {
//            System.out.println(message);
//        }
//    }

    public static void main(String[] args) {
        int m = 0;
        int n = 0;
        int k = 0;
        int countPlayers = -1;
        String gamemode = "";
        String player;
        String annotation;
        Player[] gamers;
        int[] swedishTournament;
        final boolean rusLog;

        System.out.println("Time to play mnk game");
        System.out.println("Please enter m, n, k (m, n, k > 0 and 0 < k < max(m, n), m, n, k IS NUMBERS)");
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    System.out.println("Enter m");
                    m = scanner.nextInt();
                    if (m > 0) {
                        System.out.println("m is correct, m = " + m);
                        break;
                    } else {
                        System.out.println("Please, enter m > 0");
                        scanner.nextLine();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid number, please try again");
                    scanner.nextLine();
                }

            }

            while (true) {
                try {
                    System.out.println("Enter n");
                    n = scanner.nextInt();
                    if (n > 0) {
                        System.out.println("n is correct, n = " + n);
                        break;
                    } else {
                        System.out.println("Please, enter n > 0");
                        scanner.nextLine();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid number, please try again");
                    scanner.nextLine();
                }

            }


            while (true) {
                try {
                    System.out.println("Enter k");
                    k = scanner.nextInt();
                    if (k <= Math.max(m, n) && k > 0) {
                        System.out.println("k is correct, k = " + k);
                        System.out.println("Let`s choose game type");
                        break;
                    } else {
                        System.out.println("It is impossible k, please enter 0 < k < max(m, n) ");
                        scanner.nextLine();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid number, please enter the number");
                    scanner.nextLine();
                }

            }

            System.out.println("m = " + m + " n = " + n + " k = " + k);


//            while (!gamemode.equals("Base") && !gamemode.equals("SwedishTournament")) {
//                System.out.println("Enter gamemode (Base / SwedishTournament)");
//                gamemode = scanner.next();
//                scanner.nextLine();
//            }
            System.out.println("Choose type of game: Base / SwedishTournament");
            while (true) {
                gamemode = scanner.next();
                scanner.nextLine();
                if (gamemode.equals("Base") || gamemode.equals("SwedishTournament")) {
                    System.out.println("OK, time to play " + gamemode);
                    break;
                } else {
                    System.out.println("Invalid type of game, please choose from Base / SwedishTournament");
                }
            }

            if (gamemode.equals("Base")) {
                final Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
                int result;
                do {
                    result = game.play(new MNKBoard(m, n, k));
                    System.out.println("Game result: " + result);
                } while (result != 0);
            } else {
                do {
                    System.out.println("Enter how many players would play tournament");
                    if (scanner.hasNextInt()) {
                        countPlayers = scanner.nextInt();
                        if (countPlayers <= 1) {
                            System.out.println("Number must be greater than 1, please enter valid number");
                        }
                    } else {
                        System.out.println("It is not a number! Please enter valid number greater 1");
                        scanner.nextLine();
                    }
                } while (countPlayers <= 1);
                gamers = new Player[countPlayers];
                for (int i = 0; i < countPlayers; i++) {
                    System.out.println("Enter player" + i + " type. Choose from: Random / Human / Sequential / Bad");
                    while (true) {
                        player = scanner.next();
                        scanner.nextLine();
                        switch (player) {
                            case "Human" -> gamers[i] = new HumanPlayer();
                            case "Random" -> gamers[i] = new RandomPlayer(m, n);
                            case "Sequential" -> gamers[i] = new SequentialPlayer(m, n);
                            case "Bad" -> gamers[i] = new BadPlayer();
                        }
                        if (!player.equals("Human") && !player.equals("Random") && !player.equals("Sequential") && !player.equals("Bad")) {
                            System.out.println("Enter valid type of player");
                        } else {
                            break;
                        }
                    }
                }

                swedishTournament = new int[countPlayers];
                SwedishTournament.fillTable(swedishTournament, gamers, m, n, k);
                SwedishTournament.drawTable(swedishTournament);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Console was closed by your wish, If you want to play game, please rerun");
        }
    }
}