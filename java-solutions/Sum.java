public class Sum {
    public static void main(String[] args) {
        int sumhex = 0;
        for (int i = 0; i < args.length; i++) {
            String str = args[i];
            int index = 0;
            for (int j = 0; j <= str.length(); j++) {
                if ((j == str.length()) || (Character.isWhitespace(str.charAt(j)))) {
                    if (j == str.length()) {
                        if (!str.substring(index, j).isEmpty()) {
                            sumhex += Integer.parseInt(str.substring(index, j));
                        }
                    } else if (!str.substring(index, j).isEmpty()) {
                        sumhex += Integer.parseInt(str.substring(index, j));
                    }
                    index = j + 1;

                }
            }
        }
        System.out.println(sumhex);
    }
}