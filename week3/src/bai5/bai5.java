package bai5;

import java.io.File;

public class bai5 {
    public static void main(String[] args) {
        File directory = new File("D:\\JAVA\\week3\\file.txt");

        if (directory.isDirectory()) {
            String[] files = directory.list();
            System.out.println("D:\\JAVA\\week3\\file.txt");
            for (String file : files) {
                System.out.println(file);
            }
        } else {
            System.out.println("This is not a directory.");
        }
    }
}

