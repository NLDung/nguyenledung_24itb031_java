public class bai1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Sole());
        Thread t2 = new Thread(new Sochan());
        t1.start();
        t2.start();
    }
}

class Sole implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            if (i % 2 != 0) {
                System.out.println(i);
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Sochan implements Runnable {
    @Override
    public void run() {
        for (int i = 2; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
