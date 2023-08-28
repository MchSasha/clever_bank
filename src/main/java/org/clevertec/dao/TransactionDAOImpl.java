package org.clevertec.dao;

import org.clevertec.domain.Transaction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class TransactionDAOImpl implements TransactionDAO{
    public static final String SQL_INSERT_TRANSACTION =
            "INSERT INTO Transactions(SenderAccountId, RecipientAccountId, Date, Sum) " +
            "VALUES (?, ?, NOW(), ?);";

    @Override
    public int saveTransaction(Transaction transaction) {
        Integer recipientAccountId = transaction.getRecipientAccountId();

        try(Connection connection = DatabaseUtility.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_TRANSACTION)) {

            statement.setInt(1, transaction.getSenderAccountId());

            if(recipientAccountId == null)
                statement.setNull(2, Types.INTEGER);
            else {
                statement.setInt(2, transaction.getRecipientAccountId());
            }

            statement.setDouble(3, transaction.getSum());

            return statement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}

// искл