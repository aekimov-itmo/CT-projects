import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReverseRotate {
    public static void main(String[] args) {
        try {
            SimplifiedScanner strScanner = new SimplifiedScanner(System.in);
            int[][] base = new int[1][1];
            int i = 0;
            int[] strlength = new int[1];

            while (strScanner.hasNextLine()) {
                if (base.length - 1 == i) {
                    base = Arrays.copyOf(base, base.length * 2);
                }
                base[i] = new int[1];
                int j = 0;
                SimplifiedScanner intScanner = new SimplifiedScanner(strScanner.nextLine());
                while (intScanner.hasNext()) {
                    if (base[i].length - 1 == j) {
                        base[i] = Arrays.copyOf(base[i], base[i].length * 2);
                    }
                    base[i][j] = intScanner.nextInt();
                    j++;
                }
                intScanner.close();
                if (strlength.length - 1 == i) {
                    strlength = Arrays.copyOf(strlength, strlength.length * 2);
                }
                strlength[i] = j;
                base[i] = Arrays.copyOf(base[i], j);
                i++;
            }
            strScanner.close();
            base = Arrays.copyOf(base, i);

            int maxstrlength = 0;
            for (int strlen : strlength) {
                if (maxstrlength < strlen) {
                    maxstrlength = strlen;
                }
            }

//            List<List<Integer>> resultlist = new ArrayList<>();
//            for (int index = 0; index < maxstrlength; index++) {
//                resultlist.add(new ArrayList<>());
//            }
//            for (int[] ints : base) {
//                for (int line = 0; line < ints.length; line++) {
//                    resultlist.get(line).add(ints[line]);
//                }
//            }
//            for (List<Integer> line : resultlist) {
//                for (int index = line.size() - 1; index >= 0; index--) {
//                    System.out.print(line.get(index) + " ");
//                }
//                System.out.println();
//            }
            int[] finaloutputSizes = new int[maxstrlength];
            int[][] finaloutput = new int[maxstrlength][1];
            for (int index = 0; index < base.length; index++) {

                for (int jindex = 0; jindex < strlength[index]; jindex++) {
                    while (finaloutput[jindex].length - 1 == finaloutputSizes[jindex]) {
                        finaloutput[jindex] = Arrays.copyOf(finaloutput[jindex], finaloutput[jindex].length * 2);
                    }
//                    System.err.print(base[index][jindex] + " ");
                    finaloutput[jindex][finaloutputSizes[jindex]] = base[index][jindex];
                    finaloutputSizes[jindex]++;
                }
//                System.err.println();
//                finaloutput[index] = Arrays.copyOf(finaloutput[index], jindex);
            }
//            System.err.println("-----");

            for (i = 0; i < finaloutput.length; i++) {
                for (int j = finaloutputSizes[i] - 1; j >= 0; j--) {
                    System.out.print(finaloutput[i][j] + " ");
                }
                System.out.println();
            }
//            for (int column = 0; column <= maxstrlength - 1; column++) {
//                for (int str = base.length - 1; str >= 0; str--) {
//                    if (column < base[str].length) {
//                        System.out.print(base[str][column] + " ");
//                    }
//                }
//                System.out.println();
//            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage() + "Неверный формат числа");
        } catch (IOException e) {
            System.err.println(e.getMessage() + " ");
        }

    }
}
