import java.util.Random;

class RandomOddThread extends Thread {
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);  // Giả lập tác vụ mất thời gian (1 giây)
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            int randomNumber;
            do {
                randomNumber = random.nextInt(10);  // Sinh số ngẫu nhiên từ 0 đến 9
            } while (randomNumber % 2 == 0);  // Đảm bảo số ngẫu nhiên là số lẻ
            System.out.println( randomNumber );
        }
    }
}

class RandomEvenThread extends Thread {
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1500);  // Giả lập tác vụ mất thời gian (1.5 giây)
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            int randomNumber;
            do {
                randomNumber = random.nextInt(10);  // Sinh số ngẫu nhiên từ 0 đến 9
            } while (randomNumber % 2 != 0);  // Đảm bảo số ngẫu nhiên là số chẵn
            System.out.println( randomNumber );
        }
    }
}

public class bai1 {
    public static void main(String[] args) {
        // Tạo các đối tượng luồng
        Thread randomOddThread = new RandomOddThread();
        Thread randomEvenThread = new RandomEvenThread();

        // Bắt đầu các luồng
        randomOddThread.start();
        randomEvenThread.start();

        try {
            // Chờ cho các luồng kết thúc
            randomOddThread.join();
            randomEvenThread.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Hoàn thành!");
    }
}
