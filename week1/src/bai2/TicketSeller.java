class TicketSeller extends Thread {
    private static int availableTickets = 10; // Số vé có sẵn
    private String customerName; // Tên khách hàng

    // Constructor để truyền tên khách hàng
    public TicketSeller(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void run() {
        synchronized (TicketSeller.class) {
            if (availableTickets > 0) {
                // Giảm số vé còn lại khi khách hàng đặt vé
                availableTickets--;
                System.out.println(customerName + " đã đặt một vé. Vé còn lại: " + availableTickets);
            } else {
                System.out.println(customerName + " không thể đặt vé, vé đã hết.");
            }
        }
    }

    public static void main(String[] args) {
        // Tạo và bắt đầu các luồng đặt vé
        TicketSeller customer1 = new TicketSeller("Khách hàng 1");
        TicketSeller customer2 = new TicketSeller("Khách hàng 2");
        TicketSeller customer3 = new TicketSeller("Khách hàng 3");

        customer1.start();
        customer2.start();
        customer3.start();
    }
}
