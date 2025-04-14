public class bai2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new So());
        t1.start();
    }
}

class So implements Runnable {
    @Override
    public void run() {
        for (int i = 10; i >= 0; i--) {
            System.out.println(i);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

