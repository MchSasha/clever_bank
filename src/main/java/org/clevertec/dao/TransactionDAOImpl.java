package org.clevertec.dao;

import org.clevertec.domain.Transaction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO{
    public static final String SQL_INSERT_TRANSACTION =
            "INSERT INTO Transactions(SenderAccountId, RecipientAccountId, Date, Sum) " +
            "VALUES (?, ?, NOW(), ?);";

    @Override
    public int saveTransaction(Transaction transaction) {
        try(Connection connection = DatabaseUtility.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_TRANSACTION)) {

            statement.setInt(1, transaction.getSenderAccountId());
            statement.setInt(2, transaction.getRecipientAccountId());
            statement.setDouble(3, transaction.getSum());

            return statement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Transaction> getInitiatedTransactions(String accountNumber, Date from, Date to) {
        return null;
    }

    @Override
    public List<Transaction> getNotInitiatedTransactions(String accountNumber, Date from, Date to) {
        return null;
    }
}

// искл