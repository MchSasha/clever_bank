package org.clevertec.dao;

import org.clevertec.domain.Transaction;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDAOImplTest {

    @Test
    void saveTransaction() {
        TransactionDAO transactionDAO = new TransactionDAOImpl();
        int rowsAffected;
        Transaction transaction = new Transaction(1, 1, Date.valueOf(LocalDate.now()), 9999.9);
        try {
            rowsAffected = transactionDAO.saveTransaction(transaction);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, rowsAffected);
    }

    @Test
    void getInitiatedTransactions() {

    }

    @Test
    void getNotInitiatedTransactions() {
    }
}