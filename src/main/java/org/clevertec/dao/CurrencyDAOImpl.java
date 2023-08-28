package org.clevertec.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyDAOImpl implements CurrencyDAO{
    public static final String SQL_SELECT_NAME =
            "SELECT Name " +
            "FROM Currencies " +
            "WHERE CurrencyId = ?;";

    @Override
    public String getName(int currencyId) {
        try(Connection connection = DatabaseUtility.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_NAME)) {

            statement.setInt(1, currencyId);

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
