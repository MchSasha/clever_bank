package org.clevertec.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO{
    public static final String SQL_SELECT_NAME =
            "SELECT Name " +
            "FROM Users " +
            "WHERE UserId = ?;";

    @Override
    public String getName(int userId) {
        try(Connection connection = DatabaseUtility.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_NAME)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("Name");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
