import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            // Thêm đơn hàng mới
            List<OrderItem> items = new ArrayList<>();
            items.add(new OrderItem(1, 2)); // Sản phẩm 1, số lượng 2
            items.add(new OrderItem(2, 1)); // Sản phẩm 2, số lượng 1
            addNewOrder(1, items);

            // Truy xuất lịch sử đơn hàng
            List<Order> orders = getOrderHistory(1);
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getId() + " - Date: " + order.getOrderDate());
                System.out.println("Total Price: " + getOrderTotal(order.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
