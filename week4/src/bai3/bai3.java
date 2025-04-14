package bai3;

import java.io.*;

public class bai3 {
    public static void main(String[] args) {
        String inputFile = "D:\\JAVA\\week4\\src\\bai1\\filein.txt"; // Đường dẫn file đầu vào
        String outputFile = "D:\\JAVA\\week4\\src\\bai1\\fileout.txt"; // Đường dẫn file đầu ra

        // Tạo một thread đọc file và ghi dữ liệu vào file output
        readfile rf = new readfile(inputFile, outputFile);
        rf.start();  // Bắt đầu thread đọc và ghi file
        try {
            rf.join();  // Đợi thread kết thúc
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class readfile extends Thread {
    private String inputFilename;
    private String outputFilename;

    public readfile(String inputFilename, String outputFilename) {
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilename));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilename))) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);  // In ra dòng đọc được
                bw.write(line);           // Ghi dòng vào file
                bw.newLine();             // Thêm dòng mới sau khi ghi
            }
            System.out.println("Dữ liệu đã được ghi vào file thành công!");
        } catch (IOException e) {
            e.printStackTrace();  // Bắt lỗi nếu có
        }
    }
}
