package org.clevertec.dao;

import java.io.IOException;
import java.sql.*;

public class AccountDAOImpl implements AccountDAO{
    public static final String SQL_UPDATE_BALANCE =
            "UPDATE Accounts " +
            "SET Balance = Balance + ? " +
            "WHERE AccountId = ?";

    public static final String SQL_ADD_BALANCE_CONDITION = " AND Balance > ?";

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
        StringBuilder query = new StringBuilder(SQL_UPDATE_BALANCE);
        if (sum < 0) {
            query.append(SQL_ADD_BALANCE_CONDITION);
        }

        try(Connection connection = DatabaseUtility.getConnection();
            PreparedStatement statement = connection.prepareStatement(query.toString())) {

            statement.setDouble(1, sum);
            statement.setInt(2, accountId);
            if(sum < 0)
                statement.setDouble(3, Math.abs(sum));

            return statement.executeUpdate();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int transferMoney(int senderAccountId, int recipientAccountId, double sum) {
        try(Connection connection = DatabaseUtility.getConnection()) {
            connection.setAutoCommit(false);
            try(PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BALANCE + SQL_ADD_BALANCE_CONDITION)) {
                if (senderAccountUpdateTry(senderAccountId, sum, statement) == 0) {
                    return 0;
                }
            } catch (SQLException e){
                connection.setAutoCommit(true);
            }

            try(PreparedStatement statement1 = connection.prepareStatement(SQL_UPDATE_BALANCE);
                PreparedStatement statement2 = connection.prepareStatement(TransactionDAOImpl.SQL_INSERT_TRANSACTION)) {
                prepareSenderAccountAndTransactionUpdates(senderAccountId, recipientAccountId, sum, statement1, statement2);

                statement1.executeUpdate();
                statement2.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return 1;
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

    private void prepareSenderAccountAndTransactionUpdates(int senderAccountId, int recipientAccountId, double sum, PreparedStatement statement1, PreparedStatement statement2) throws SQLException {
        statement1.setDouble(1, sum);
        statement1.setInt(2, recipientAccountId);

        statement2.setInt(1, senderAccountId);
        statement2.setInt(2, recipientAccountId);
        statement2.setDouble(3, -sum);
    }

    private int senderAccountUpdateTry(int senderAccountId, double sum, PreparedStatement statement) throws SQLException {
        statement.setDouble(1, -sum);
        statement.setInt(2, senderAccountId);
        statement.setDouble(3, Math.abs(sum));

        return statement.executeUpdate();
    }
}

//в updateBal меняю на аккаунтид

//анчекд искл в обновитьСчет
//анчекд искл в получить ай ди

// нужен ли трансфер если можно реализовать через сохранениеТранзакции и 2 обновленияБаланса



