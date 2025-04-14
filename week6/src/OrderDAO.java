import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public void createOrder(int customerId, List<OrderItem> items) throws SQLException {
        String orderSql = "INSERT INTO orders (customer_id) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);
                addOrderItems(orderId, items);
            }
        }
    }

    private void addOrderItems(int orderId, List<OrderItem> items) throws SQLException {
        String itemSql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(itemSql)) {
            for (OrderItem item : items) {
                stmt.setInt(1, orderId);
                stmt.setInt(2, item.getProductId());
                stmt.setInt(3, item.getQuantity());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    public List<Order> getOrderHistoryByCustomerId(int customerId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt("id"), rs.getInt("customer_id"), rs.getTimestamp("order_date"));
                orders.add(order);
            }
        }
        return orders;
    }

    public double calculateTotalOrderPrice(int orderId) throws SQLException {
        double total = 0;
        String sql = "SELECT p.price, oi.quantity FROM order_items oi JOIN products p ON oi.product_id = p.id WHERE oi.order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                total += rs.getDouble("price") * rs.getInt("quantity");
            }
        }
        return total;
    }
}
