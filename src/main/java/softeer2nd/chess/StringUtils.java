package softeer2nd.chess;

public class StringUtils {

    private static final String NEWLINE = System.getProperty("line.separator");

    private StringUtils() {
    }

    public static String appendNewLine(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str).append(NEWLINE);
        return sb.toString();
    }
}
