public class SumHex {
    private static int a(String str, int index, int j) {
        String slice = str.substring(index, j);
        if (slice.length() > 2 && Character.toLowerCase(slice.charAt(1)) == 'x' ) {
            return Integer.parseUnsignedInt(str.substring(index + 2, j), 16);
        } else {
           return Integer.parseInt(slice);
        }
    }

    public static void main(String[] args) {
        int sumhex = 0;
        for (int i = 0; i < args.length; i++) {
            String str = args[i];
            int index = 0;
            for (int j = 0; j <= str.length(); j++) {
                if (!(j == str.length() || (Character.isWhitespace(str.charAt(j))))) {
                    continue;
                }
                String substr = str.substring(index, j);
                if (!substr.isEmpty()) {
                    sumhex += a(str, index, j);
                }
                index = j + 1;
            }
        }
        System.out.println(sumhex);
    }
}