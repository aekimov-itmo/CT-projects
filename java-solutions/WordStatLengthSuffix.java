import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class WordStatLengthSuffix {
    private static boolean isAlphaDashOrHyphenOrApostrophe(char letter) {
        return Character.isLetter(letter) || Character.getType(letter) == Character.DASH_PUNCTUATION || letter == '\'';
    }

    private static void processBuffer(char[] bufArr, int charsRead, StringBuilder sb, LinkedHashMap<String, Integer> wf) {
        for (int i = 0; i < charsRead; i++) {
            char c = bufArr[i];
            if (isAlphaDashOrHyphenOrApostrophe(c)) {
                sb.append(c);
            } else {
                insertIntoMap(sb, wf);
                sb.setLength(0);
            }
        }
    }

    private static void insertIntoMap(StringBuilder sb, LinkedHashMap<String, Integer> wf) {
        String word = sb.toString().toLowerCase();
        int len = word.length();
        if (len > 1) {
            wf.put(word.substring(len - (len / 2)),
                    wf.getOrDefault(word.substring(len - (len / 2)),
                            0) + 1);
        }
    }

    public static void main(String[] args) {
        final int BUFFER_SIZE = 8192;
        try (SimplifiedScanner scanner = new SimplifiedScanner(new FileInputStream(args[0]))) {
            StringBuilder strbuildword = new StringBuilder();
            LinkedHashMap<String, Integer> wordsfreq = new LinkedHashMap<>();
            char[] bufferArray = new char[BUFFER_SIZE];
            int charsRead;
            while ((charsRead = scanner.read(bufferArray)) != -1) {
                processBuffer(bufferArray, charsRead, strbuildword, wordsfreq);
            }

            if (!strbuildword.isEmpty()) {
                insertIntoMap(strbuildword, wordsfreq);
            }
            List<Map.Entry<String, Integer>> listwordsfreq = new ArrayList<>(wordsfreq.entrySet());
            listwordsfreq.sort(Comparator.comparing(pair -> pair.getKey().length()));
            LinkedHashMap<String, Integer> wordsfreqByLength = new LinkedHashMap<>();
            for (Map.Entry<String, Integer> pair : listwordsfreq) {
                wordsfreqByLength.put(pair.getKey(), pair.getValue());
            }

            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8), 8192)) {
                wordsfreqByLength.forEach((word, freq) -> {
                            try {
                                writer.write(word + " " + freq);
                                writer.newLine();
                            } catch (IOException e) {
                                System.err.println(e.getMessage());
                            }
                        }
                );
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}