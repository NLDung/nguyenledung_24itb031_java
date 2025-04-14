package bai1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class bai1 {
    public static void main(String[] args) {
        try {
            FileInputStream inputStream = new FileInputStream("D:\\JAVA\\week3\\file.txt");
            FileOutputStream outputStream = new FileOutputStream("D:\\JAVA\\week3\\file.txt");

            int byteData;
            while ((byteData = inputStream.read()) != -1) {
                outputStream.write(byteData);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("File copied successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
