package bai2;

import java.io.*;
import java.util.Scanner;

public class bai2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "D:\\JAVA\\week3\\file.txt";
        System.out.println("Nhập dữ liệu bạn muốn lưu vào file (gõ 'exit' để dừng):");

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line;
            while (true) {
                line = scanner.nextLine();
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Dữ liệu đã được lưu vào file: " + filePath);
        } catch (IOException e) {
            System.out.println("Có lỗi khi ghi dữ liệu vào file.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
