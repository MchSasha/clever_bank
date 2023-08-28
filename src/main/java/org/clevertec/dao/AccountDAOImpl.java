package org.clevertec.dao;

import java.io.IOException;
import java.sql.*;

public class AccountDAOImpl implements AccountDAO{
    public static final String SQL_UPDATE_BALANCE =
            "UPDATE Accounts " +
            "SET Balance = (" +
            "SELECT Balance WHERE AccountId = ?) + ? " +
            "WHERE AccountId = ? AND Balance > ?;";

    public static final String SQL_SELECT_ID =
            "SELECT AccountId " +
            "FROM Accounts " +
            "WHERE Number = ?;";

    public static final String SQL_COUNT_TOTAL_INCOME =
            "SELECT -1 * (SELECT COALESCE(SUM(Sum),0) " +
                    "FROM Transactions " +
                    "WHERE RecipientAccountId = ? AND SenderAccountId <> ? AND Sum < 0 AND Date BETWEEN ? AND ?)" +
                    " + " +
                    "(SELECT COALESCE(SUM(Sum),0)" +
                    "FROM Transactions " +
                    "WHERE SenderAccountId = ? AND RecipientAccountId IS NULL AND Sum > 0 and Date BETWEEN ? AND ?) " +
            "AS TotalIncome;";

    public static final String SQL_COUNT_TOTAL_WITHDRAWAL =
            "SELECT (SELECT COALESCE(SUM(Sum),0) " +
                    "FROM Transactions " +
                    "WHERE RecipientAccountId <> ? and SenderAccountId = ? AND Sum < 0 AND Date BETWEEN ? AND ?) +  " +
                    "(SELECT COALESCE(SUM(Sum),0) " +
                    "FROM Transactions " +
                    "WHERE SenderAccountId = ? AND RecipientAccountId IS NULL AND Sum < 0  AND Date BETWEEN ? AND ?) " +
            "AS TotalWithdrawal;";

    @Override
    public int updateBalance(Integer accountId, double sum) {

        try(Connection connection = DatabaseUtility.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BALANCE)) {

            statement.setInt(1, accountId);
            statement.setDouble(2, sum);
            statement.setInt(3, accountId);
            if(sum < 0)
                statement.setDouble(4, Math.abs(sum));
            else statement.setDouble(4, Double.NEGATIVE_INFINITY);

            return statement.executeUpdate();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void transferMoney(String senderAccountNumber, String recipientAccountNumber, double sum) {

    }

    @Override
    public int getAccountId(String accountNumber) {
        try(Connection connection = DatabaseUtility.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ID)) {

            statement.setString(1, accountNumber);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("AccountId");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }


    @Override
    public int getTotalIncome(int accountId, Date from, Date to) {
        try(Connection connection = DatabaseUtility.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_COUNT_TOTAL_INCOME)) {

            statement.setInt(1, accountId);
            statement.setInt(2, accountId);
            statement.setDate(3, from);
            statement.setDate(4, to);
            statement.setInt(5, accountId);
            statement.setDate(6, from);
            statement.setDate(7, to);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("TotalIncome");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    @Override
    public int getTotalWithdrawal(int accountId, Date from, Date to) {
        try(Connection connection = DatabaseUtility.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_COUNT_TOTAL_WITHDRAWAL)) {

            statement.setInt(1, accountId);
            statement.setInt(2, accountId);
            statement.setDate(3, from);
            statement.setDate(4, to);
            statement.setInt(5, accountId);
            statement.setDate(6, from);
            statement.setDate(7, to);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("TotalWithdrawal");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
}

//в updateBal меняю на аккаунтид

//анчекд искл в обновитьСчет
//анчекд искл в получить ай ди

// нужен ли трансфер если можно реализовать через сохранениеТранзакции и 2 обновленияБаланса