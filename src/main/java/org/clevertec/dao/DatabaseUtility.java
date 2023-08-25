package org.clevertec.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtility {
    public static final String PROPERTIES_PATH = "src/main/resources/connection.properties";

    public static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        FileInputStream stream = new FileInputStream(PROPERTIES_PATH);
        properties.load(stream);

        return DriverManager.getConnection(
                properties.getProperty("db.URL"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password"));
    }
}
