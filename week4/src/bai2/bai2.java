package bai2;

import java.io.*;

public class bai2 {
    public static void main(String[] args) {
        String filename = "D:\\JAVA\\week4\\src\\bai1\\fileout.txt";
        writeToFile(filename);
    }

    public static void writeToFile(String filename) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Nhập dữ liệu vào file (nhấn 'exit' để kết thúc):");

            String line;
            while (!(line = reader.readLine()).equals("exit")) {
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Dữ liệu đã được ghi vào file thành công!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
