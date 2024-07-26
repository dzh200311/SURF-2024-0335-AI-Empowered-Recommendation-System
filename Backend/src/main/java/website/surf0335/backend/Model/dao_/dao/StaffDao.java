package website.surf0335.backend.Model.dao_.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import website.surf0335.backend.Model.dao_.domain.Staff;
import website.surf0335.backend.Model.dao_.utils.DruidJdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class StaffDao extends BasicDao<Staff> {
    private QueryRunner qr = new QueryRunner();

    //dml
    public Long queryCountByRole(int role) {
        Connection connection = null;
        // SQL statement using the table name directly
        String sql = "SELECT COUNT(*) FROM staff WHERE role = ?";
        try {
            connection = DruidJdbcUtils.getConnection();
            // Using ScalarHandler to handle the returned result, assuming it's an integer count
            return qr.query(connection, sql, new ScalarHandler<Long>(), role);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidJdbcUtils.close(null, null, connection);
        }
    }

    public int getLastStaffId() {
        Connection connection = null;
        try {
            connection = DruidJdbcUtils.getConnection();
            String sql = "SELECT MAX(staff_id) AS last_staff_id FROM staff";
            return qr.query(connection, sql, new ScalarHandler<Integer>());
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving last staff ID", e);
        } finally {
            DruidJdbcUtils.close(null, null, connection);
        }
    }



}
