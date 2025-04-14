package bai3;


import java.io.*;

public class bai3 {
    public static void main(String[] args) {
        String filePath = "D:\\JAVA\\week3\\file.txt";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            System.out.println("Nội dung của file là:");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Có lỗi khi đọc file:");
            e.printStackTrace();
        }
    }
}
