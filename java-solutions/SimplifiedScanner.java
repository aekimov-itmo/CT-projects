import java.io.*;
import java.nio.charset.StandardCharsets;


public class SimplifiedScanner implements AutoCloseable {
    private static final int SIZE_OF_BUFFER = 8192;
    private final Reader reader;
    private final char[] chars = new char[SIZE_OF_BUFFER];
    private int sizeOfBuffer = 0;
    private int position = 0;
    private Character backchar = null;


    public SimplifiedScanner() throws IOException {
        this(System.in);
    }

    public SimplifiedScanner(InputStream in) {
        this(new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8), 8192));
    }

    public SimplifiedScanner(String string) {
        this(new StringReader(string));
    }

    private SimplifiedScanner(Reader reader) {
        this.reader = reader;
    }

    // :NOTE:
    // Predicate<Character>
    // public interface Checker {
    //     boolean test(char ch);
    //}

    public boolean isPunctuationChar (char ch) {
        int type = Character.getType(ch);
        return type == Character.START_PUNCTUATION ||
                type == Character.END_PUNCTUATION;
    }

    private void fill() throws IOException {
        sizeOfBuffer = reader.read(chars);
        position = 0;
    }

    private int readChar() throws IOException {
        if (position >= sizeOfBuffer) {
            fill();
            if (sizeOfBuffer == -1) {
                return -1;
            }
        }
        return chars[position++];
    }

    private int nextChar() throws IOException {
        if (position >= sizeOfBuffer) {
            fill();
            if (sizeOfBuffer == -1) {
                return -1;
            }
        }
        return chars[position];
    }

    private void toRightChar() throws IOException {
        int ch;
        while ((ch = nextChar()) != -1 && (Character.isWhitespace((char) ch) || isPunctuationChar((char) ch))) {
            readChar();
        }
    }

    public boolean hasNext() throws IOException {
        toRightChar();
        return nextChar() != -1;
    }

    public boolean hasNextLine() throws IOException {
        return nextChar() != -1;
    }

    public String next() throws IOException {
        toRightChar();
        StringBuilder strbuilder = new StringBuilder();
        int ch;
        while ((ch = nextChar()) != -1 && (!Character.isWhitespace(ch) && !isPunctuationChar((char) ch))) {
            strbuilder.append((char) readChar());
        }
        return strbuilder.toString();
    }

    public int nextInt() throws IOException {
        try {
            return Integer.parseInt(next());
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage() + "Enter the number please");
            throw new NumberFormatException();

        }
    }

    StringBuilder strbuilder = new StringBuilder();

    public String nextLine() throws IOException {
        int ch;
        String lineSep = System.lineSeparator();
        int sepLen = lineSep.length();
        int index = 0;
        while ((ch = readChar()) != -1) {
            strbuilder.append((char) ch);
            int sepchar = lineSep.charAt(index);
            if (ch == sepchar) {
                index++;
                if (index == sepLen) {
                    strbuilder.setLength(strbuilder.length() - sepLen);
                    break;
                }
            } else {
                index = 0;

            }
        }

        String res = strbuilder.toString();
        strbuilder.setLength(0);
        return res;
    }


    public int read(char[] cbuf, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }

        int finalRead = 0;
        if (position < sizeOfBuffer) {
            int possibleToRead = Math.min(len, sizeOfBuffer - position);
            System.arraycopy(chars, position, cbuf, off, possibleToRead);
            position += possibleToRead;
            finalRead += possibleToRead;
        }

        while (finalRead < len && sizeOfBuffer != -1) {
            fill();
            if (sizeOfBuffer == -1) {
                break;
            }
            int toRead = Math.min(len - finalRead, sizeOfBuffer);
            System.arraycopy(chars, position, cbuf, off + finalRead, toRead);
            position += toRead;
            finalRead += toRead;
        }

        return finalRead == 0 && sizeOfBuffer == -1 ? -1 : finalRead;
    }

    public int read(char[] cbuf) throws IOException {
        return read(cbuf, 0, cbuf.length);
    }

    public int read() throws IOException{
        if (backchar != null) {
            char res = backchar;
            backchar = null;
            return res;
        }

        if (sizeOfBuffer <= position) {
            fill();
            if (sizeOfBuffer == -1) {
                return -1;
            }
        }
        return chars[position++];
    }

    public void unread(char c) {
        backchar = c;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}