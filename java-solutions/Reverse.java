import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Reverse {
    public static void main(String[] args) {
        try {
            SimplifiedScanner strScanner = new SimplifiedScanner();
            int[][] base = new int[1][1];
            int i = 0;
            while (strScanner.hasNextLine()) {
                if (base.length - 1 == i) {
                    base = Arrays.copyOf(base, base.length * 2);
                }
                base[i] = new int[1];
                int j = 0;
                String line = strScanner.nextLine();
                SimplifiedScanner intScanner = new SimplifiedScanner(line);
                while (intScanner.hasNext()) {
                    if (base[i].length - 1 == j) {
                        base[i] = Arrays.copyOf(base[i], base[i].length * 2);
                    }
                    try {
                        base[i][j] = intScanner.nextInt();
                        j++;
                    } catch (NumberFormatException | InputMismatchException e) {
                        intScanner.next();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                        break;
                    }

                }
                intScanner.close();
                base[i] = Arrays.copyOf(base[i], j);
                i++;
            }
            strScanner.close();
            base = Arrays.copyOf(base, i);
            for (int p = i - 1; p >= 0; p-- ) {
                for (int k = base[p].length - 1; k >= 0; k-- ) {
                    System.out.print(base[p][k] + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }




    }
}
