package it.beta80group.stud.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.beta80group.stud.services.HelloWorldService;
import it.beta80group.stud.utils.ApplicationPropertyLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private ApplicationPropertyLoader propertyLoader;

    final Logger logger = LogManager.getLogger(DataSource.class);

    private static DataSource INSTANCE;

    private void init(){
        propertyLoader = ApplicationPropertyLoader.getInstance();
        config.setJdbcUrl(propertyLoader.getProperty("db.url"));// "jdbc:h2:mem:test");
        config.setUsername(propertyLoader.getProperty("db.username"));
        config.setPassword(propertyLoader.getProperty("db.password"));
        config.setMaximumPoolSize(10);
        config.setDriverClassName(propertyLoader.getProperty("db.driver"));
        ds = new HikariDataSource(config);
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    private DataSource(){
        init();
    }

    public static DataSource getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DataSource();
        }
        return INSTANCE;
    }
}
