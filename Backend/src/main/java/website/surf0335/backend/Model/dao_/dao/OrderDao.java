package website.surf0335.backend.Model.dao_.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import website.surf0335.backend.Model.dao_.domain.Order;
import website.surf0335.backend.Model.dao_.utils.DruidJdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDao extends BasicDao<Order> {

    private QueryRunner qr = new QueryRunner();

    public int getLastOrderId() {
        Connection connection = null;
        try {
            connection = DruidJdbcUtils.getConnection();
            String sql = "SELECT MAX(order_id) AS last_order_id FROM `order`";
            return qr.query(connection, sql, new ScalarHandler<Integer>());
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving last order ID", e);
        } finally {
            DruidJdbcUtils.close(null, null, connection);
        }
    }
}
