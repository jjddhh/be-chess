package softeer2nd.chess.utils;

public class StringUtil {
    private static final String NEWLINE = System.getProperty("line.separator");

    private StringUtil() {}

    public static String appendNewLine(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str).append(NEWLINE);
        return sb.toString();
    }

    public static String getOriginPositionFormat(int row, int col) {
        char originCol = (char) (col + 'a');
        int originRow = 8 - row;
        StringBuilder sb = new StringBuilder();
        String origin = sb.append(originCol).append(originRow).toString();

        return origin;
    }
}