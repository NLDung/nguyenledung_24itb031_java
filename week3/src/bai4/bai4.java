package bai4;
import java.io.*;

public class bai4 {
    public static void main(String[] args) {
        String sourceFile = "D:\\JAVA\\week3\\sourcefile.dat";
        String destinationFile = "D:\\JAVA\\week3\\destinationfile.dat";

        try (DataInputStream dataIn = new DataInputStream(new FileInputStream(sourceFile));
             DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(destinationFile))) {
            int intValue = dataIn.readInt();
            double doubleValue = dataIn.readDouble();
            String stringValue = dataIn.readUTF();
            dataOut.writeInt(intValue);
            dataOut.writeDouble(doubleValue);
            dataOut.writeUTF(stringValue);

            System.out.println("Dữ liệu đã được sao chép từ file nguồn sang file đích.");
        } catch (IOException e) {
            System.out.println("Có lỗi khi đọc hoặc ghi dữ liệu.");
            e.printStackTrace();
        }
    }
}
