package website.surf0335.backend.Model.dao_.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import website.surf0335.backend.Model.dao_.domain.User;
import website.surf0335.backend.Model.dao_.utils.DruidJdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDao extends BasicDao<User> {
    private QueryRunner qr = new QueryRunner();

    //dml
    public Long queryCount(String tableName) {
        Connection connection = null;
        // SQL 语句，直接使用表名变量
        String sql = "SELECT COUNT(*) FROM " + tableName;
        try {
            connection = DruidJdbcUtils.getConnection();
            // 使用 ScalarHandler 来处理返回的结果，因为我们知道返回的是一个数量（整数）
            return qr.query(connection, sql, new ScalarHandler<Long>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidJdbcUtils.close(null, null, connection);
        }
    }

}
