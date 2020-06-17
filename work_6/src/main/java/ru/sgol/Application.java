package ru.sgol;

import java.io.*;
import java.util.Scanner;

public class Application {

    private final static String FIRST_FILE_TEXT = "I am first file!\nI will The Best";
    private final static String SECOND_FILE_TEXT = "Im newest file\nI will The Best!";

    public static void main(String[] args) throws FileNotFoundException {

        String pathnameFile1 = "file1.txt";
        String pathnameFile2 = "file2.txt";

        File file1 = new File(pathnameFile1);
        File file2 = new File(pathnameFile2);

        PrintStream outputStream = new PrintStream(new FileOutputStream(file1));
        outputStream.println(FIRST_FILE_TEXT);
        outputStream.close();

        outputStream = new PrintStream(new FileOutputStream(file2));
        outputStream.println(SECOND_FILE_TEXT);
        outputStream.close();

        filesJoin (pathnameFile1,pathnameFile2);

    }

    private static void filesJoin (String file1, String file2) throws FileNotFoundException {
        File joinFile = new File("file3.txt");

        PrintStream outputStream = new PrintStream(new FileOutputStream(joinFile));

        writeFileInOutputStream(file1, outputStream);
        writeFileInOutputStream(file2, outputStream);

        outputStream.close();
    }

    private static void writeFileInOutputStream(String file1, PrintStream outputStream) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(file1));
        while (scanner.hasNext()){
            outputStream.println(scanner.nextLine());
        }
        scanner.close();
    }
}
