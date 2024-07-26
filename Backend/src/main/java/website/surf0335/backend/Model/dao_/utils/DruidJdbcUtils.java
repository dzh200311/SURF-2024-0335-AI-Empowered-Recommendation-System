package website.surf0335.backend.Model.dao_.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DruidJdbcUtils {
    private static DataSource ds;

    static {
        try {
            Properties properties = new Properties();
            String fileSeparator = System.getProperty("file.separator");
            String filePath = System.getProperty("user.dir") + fileSeparator + "Backend" + fileSeparator + "src" + fileSeparator + "druid.properties";
            properties.load(new FileInputStream(filePath));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    get connection
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /*
    release connection
     */
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
