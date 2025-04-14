class BanAn {
    private final int MAX_SIZE = 5;
    private int currentSize = 0;
    public synchronized void nauMon() throws InterruptedException {
        while (currentSize == MAX_SIZE) {
            System.out.println("Bàn đầy, đầu bếp chờ...");
            wait();
        }
        currentSize++;
        System.out.println("Đầu bếp đã nấu xong một món. Số món trên bàn: " + currentSize);
        notify();
    }

    // Khách hàng ăn món
    public synchronized void anMon() throws InterruptedException {
        while (currentSize == 0) {
            System.out.println("Bàn trống, khách hàng chờ...");
            wait();
        }
        currentSize--;
        System.out.println("Khách hàng đã ăn một món. Số món trên bàn: " + currentSize);
        notify();
    }
}


class DauBep extends Thread {
    private BanAn banAn;

    public DauBep(BanAn banAn) {
        this.banAn = banAn;
    }

    @Override
    public void run() {
        try {
            while (true) {
                banAn.nauMon();
                this.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class KhachHang extends Thread {
    private BanAn banAn;

    public KhachHang(BanAn banAn) {
        this.banAn = banAn;
    }

    @Override
    public void run() {
        try {
            while (true) {
                banAn.anMon();
                this.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class bai3 {
    public static void main(String[] args) {
        BanAn banAn = new BanAn();
        DauBep dauBep = new DauBep(banAn);
        KhachHang khachHang = new KhachHang(banAn);

        dauBep.start();
        khachHang.start();
    }
}