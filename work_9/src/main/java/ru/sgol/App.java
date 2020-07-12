package ru.sgol;


import java.util.Arrays;

public class App {
    private static final String ARRAY_STRING = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";

    public static void main(String[] args) {

        try {
            System.out.println(Arrays.deepToString(convertStringArrayToInt(convertStringToArray(ARRAY_STRING))));
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }

    private static String[][] convertStringToArray (String inString) throws StringConvertorException {
        String[] lines= inString.split("\n");
        String[][] outArray = new String[lines.length][];

        if (outArray.length != 4) {
            throw new StringConvertorException("Не верное колличество столбцов");
        }

        for (int i = 0; i < lines.length; i++) {
            outArray[i] = lines[i].split(" ");
        }

        for (String[] s: outArray){
            if (s.length != 4) {
                throw new StringConvertorException("Не верное колличество строк");
            }
        }

        return outArray;
    }

    private static Integer[][] convertStringArrayToInt (String[][] inArray) throws IntConvertorException {
        Integer[][] outArray = new Integer[inArray.length][];

        try {
            for (int i = 0; i < inArray.length; i++) {
                outArray[i] = new Integer[inArray[i].length];
                for (int j = 0; j < inArray[i].length; j++) {
                    outArray[i][j] = Integer.parseInt(inArray[i][j]);
                }
            }
        } catch (NumberFormatException e) {
            throw new IntConvertorException("Не все элементы входного массива являются числами");
        }

        return outArray;
    }
}
