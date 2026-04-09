package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Md2Html {
    private static final HashMap<String, String> TAGS = new HashMap<>();

    static {
        TAGS.put("`", "code");
        TAGS.put("--", "s");
        TAGS.put("*", "em");
        TAGS.put("_", "em");
        TAGS.put("__", "strong");
        TAGS.put("**", "strong");
        TAGS.put("!!", "samp");
    }


    public static void main(String[] args) {
        List<String> lines;
        lines = read(args[0]);


        StringBuilder sb = new StringBuilder();
        StringBuilder blockStringBuilder = new StringBuilder();

        parseToHTML(lines, sb, blockStringBuilder);

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(args[1]),
                        StandardCharsets.UTF_8))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    private static void parseToHTML(List<String> lines, StringBuilder sb, StringBuilder blockStringBuilder) {
        int iter = 0;
        boolean flag = false;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).isEmpty() && !blockStringBuilder.isEmpty()) {
                String block = process(blockStringBuilder.toString());
                blockStringBuilder.setLength(0);
                if (flag) {
                    markHeaderToHTML(sb, "h", block.substring(iter + 1, block.length() - 1), iter);
                    iter = 0;
                    flag = !flag;
                } else {
                    markToHTML(sb, "p", block.substring(0, block.length() - 1));
                    iter = 0;
                }
                sb.append("\n");
            }

            while (!lines.get(i).isEmpty() && lines.get(i).length() > iter && lines.get(i).charAt(iter) == '#' && !flag) {
                iter++;
            }

            blockStringBuilder.append(lines.get(i));
            if (!lines.get(i).isEmpty()) {
                blockStringBuilder.append("\n");
            }
            flag = (flag || iter <= 6 && iter != 0 && Character.isWhitespace(lines.get(i).charAt(iter)));
        }
    }

    private static void markToHTML(StringBuilder sb, String tag, String block) {
        sb.append("<").append(tag).append(">");
        sb.append(block);
        sb.append("</").append(tag).append(">");
    }

    private static void markHeaderToHTML(StringBuilder sb, String tag, String block, int iter) {
        sb.append("<").append(tag).append(iter).append(">");
        sb.append(block);
        sb.append("</").append(tag).append(iter).append(">");
    }

    private static List<String> read(String file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8), 8192)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            lines.add("");
        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }
        return lines;

    }

    private static String process(String block) {
        StringBuilder blockSB = new StringBuilder();
        StringBuilder tagSB = new StringBuilder();
        String tag;

        for (int i = 0; i < block.length(); i++) {

            if (block.charAt(i) == '\\') {
                blockSB.append(block.charAt(i + 1));
                i += 1;
                continue;
            }
            tagSB.append(block.charAt(i));

            if (
                    (block.charAt(i) == '*') ||
                            (block.charAt(i) == '_') ||
                            (block.charAt(i) == '-') ||
                            (block.charAt(i) == '!')
            ) {
                if (i + 1 < block.length() && block.charAt(i) == block.charAt(i + 1)) {
                    tagSB.append(block.charAt(i + 1));
                }
            }
            tag = tagSB.toString();
            tagSB.setLength(0);
            if (TAGS.containsKey(tag)) {
                int indexOfCloseTag = findCloseTag(block.substring(i + tag.length()), tag, i + tag.length());
                if (indexOfCloseTag != -1 && block.charAt(indexOfCloseTag - 1) != '\\') {
                    String subBlock = process(block.substring(i + tag.length(), indexOfCloseTag));
                    markToHTML(blockSB, TAGS.get(tag), subBlock);
                    i = indexOfCloseTag + tag.length() - 1;
                } else {
                    blockSB.append(tag.charAt(0));
                }
                continue;
            }
            switch (block.charAt(i)) {
                case '<' -> blockSB.append("&lt;");
                case '>' -> blockSB.append("&gt;");
                case '&' -> blockSB.append("&amp;");
                default -> blockSB.append(block.charAt(i));
            }
        }
        return blockSB.toString();
    }

    private static int findCloseTag(String block, String tag, int index) {

        for (int i = 0; i < block.length(); i++) {
            boolean isTag = block.charAt(i) == tag.charAt(0);

            if (block.charAt(i) == '\\') {
                i++;
                continue;
            }
            if (i != block.length() - 1 && (tag.charAt(0) == '*' || tag.charAt(0) == '_')) {
                if (block.charAt(i) == block.charAt(i + 1) && tag.charAt(0) == block.charAt(i)) {
                    if (tag.length() == 2) {
                        return i + index;
                    } else {
                        i++;
                    }
                } else if (tag.charAt(0) == block.charAt(i) && 1 == tag.length()) {
                    return i + index;
                }
            } else if (tag.length() == 1 && isTag) {
                return i + index;
            } else if (i != block.length() - 1 && tag.length() == 2 && isTag && tag.charAt(0) == block.charAt(i + 1)) {
                return i + index;
            }
        }
        return -1;
    }
}