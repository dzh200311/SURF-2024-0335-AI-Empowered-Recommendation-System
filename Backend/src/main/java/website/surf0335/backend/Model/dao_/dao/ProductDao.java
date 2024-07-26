package website.surf0335.backend.Model.dao_.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import website.surf0335.backend.Model.dao_.domain.Product;
import website.surf0335.backend.Model.dao_.utils.DruidJdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductDao extends BasicDao<Product> {
    private QueryRunner qr = new QueryRunner();

    public int getLastProductId() {
        Connection connection = null;
        try {
            connection = DruidJdbcUtils.getConnection();
            String sql = "SELECT MAX(product_id) AS last_staff_id FROM product";
            return qr.query(connection, sql, new ScalarHandler<Integer>());
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving last product ID", e);
        } finally {
            DruidJdbcUtils.close(null, null, connection);
        }
    }
}
