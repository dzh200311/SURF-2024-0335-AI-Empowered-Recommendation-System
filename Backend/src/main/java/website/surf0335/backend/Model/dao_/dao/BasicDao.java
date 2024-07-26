package website.surf0335.backend.Model.dao_.dao;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import website.surf0335.backend.Model.dao_.utils.DruidJdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasicDao<T> {
    private QueryRunner qr = new QueryRunner();

    //dml
    public int update(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = DruidJdbcUtils.getConnection();
            int update = qr.update(connection, sql, parameters);
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidJdbcUtils.close(null, null, connection);
        }
    }

    //return multiple object
    public List<T> queryMultiple(String sql, Class<T> class_1, Object... parameters) {
        Connection connection = null;
        try {
            connection = DruidJdbcUtils.getConnection();
            return qr.query(connection, sql, new BeanListHandler<T>(class_1), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidJdbcUtils.close(null, null, connection);
        }
    }

    //return single row
    public T querySingle(String sql, Class<T> class_1, Object... parameters) {
        Connection connection = null;
        try {
            connection = DruidJdbcUtils.getConnection();
            return qr.query(connection, sql, new BeanHandler<T>(class_1), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidJdbcUtils.close(null, null, connection);
        }

    }

    //return single value
    public Object queryScalar(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = DruidJdbcUtils.getConnection();
            return qr.query(connection, sql, new ScalarHandler<>(), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidJdbcUtils.close(null, null, connection);
        }
    }

}
