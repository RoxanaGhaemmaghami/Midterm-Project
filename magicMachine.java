import java.util.*;
import java.util.stream.IntRange;

public class MagicMachine {

    private static final int BLACK_FUNCTION_REVERSE = 1;
    private static final int BLACK_FUNCTION_DOUBLE = 2;
    private static final int BLACK_FUNCTION_REPEAT = 3;
    private static final int BLACK_FUNCTION_SHIFT_RIGHT = 4;
    private static final int BLACK_FUNCTION_SWAP_CASE = 5;
    private static final int WHITE_FUNCTION_CONCATENATE = 1;
    private static final int WHITE_FUNCTION_REVERSE_APPEND = 2;
    private static final int WHITE_FUNCTION_INTERLEAVE = 3;
    private static final int WHITE_FUNCTION_CHOOSE_EVEN_LENGTH = 4;
    private static final int WHITE_FUNCTION_ADD_MOD_26 = 5;

    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // خواندن ابعاد ماشین جادویی
        int n = scanner.nextInt();
        if (n < 2 || n > 20) {
            System.err.println("Error");
            return;
        }

        // خواندن رشته ورودی
        String inputString = scanner.next();
        if (inputString.length() < 5 || inputString.length() > 20) {
            System.err.println("Error");
            return;
        }

        // ساخت ماشین جادویی
        int[][] magicMachine = generateMagicMachine(n);

        // شبیه‌سازی ماشین جادویی
        String outputString = inputString;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int functionType = magicMachine[i][j];
                if (isBlackFunction(functionType)) {
                    outputString = applyBlackFunction(functionType, outputString);
                } else {
                    if (j == 0) {
                        // سمت چپ ماشین
                        outputString = applyWhiteFunction(functionType, outputString, "");
                    } else {
                        // سمت راست ماشین
                        outputString = applyWhiteFunction(functionType, outputString, outputString);
                    }
                }
            }
        }

        // چاپ خروجی
        System.out.println(outputString);
    }

    private static int[][] generateMagicMachine(int n) {
        int[][] magicMachine = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                magicMachine[i][j] = random.nextInt(5) + 1;
            }
        }
        return magicMachine;
    }

    private static boolean isBlackFunction(int functionType) {
        return functionType >= BLACK_FUNCTION_REVERSE && functionType <= BLACK_FUNCTION_SWAP_CASE;
    }

    private static String applyBlackFunction(int functionType, String inputString) {
        switch (functionType) {
            case BLACK_FUNCTION_REVERSE:
                return new StringBuilder(inputString).reverse().toString();
            case BLACK_FUNCTION_DOUBLE:
                return inputString + inputString;
            case BLACK_FUNCTION_REPEAT:
                StringBuilder repeatedString = new StringBuilder();
                for (char c : inputString.toCharArray()) {
                    repeatedString.append(c);
                    repeatedString.append(c);
                }
                return repeatedString.toString();
            case BLACK_FUNCTION_SHIFT_RIGHT:
                return inputString.substring(1) + inputString.charAt(0);
            case BLACK_FUNCTION_SWAP_CASE:
                StringBuilder swappedCaseString = new StringBuilder();
                for (char c : inputString.toCharArray()) {
                    swappedCaseString.append(Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c));
                }
                return swappedCaseString.toString();
            default:
                return inputString;
        }
    }
}



