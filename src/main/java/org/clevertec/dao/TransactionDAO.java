package org.clevertec.dao;

import org.clevertec.domain.Transaction;

import java.sql.Date;
import java.util.List;

public interface TransactionDAO {
    int saveTransaction(Transaction transaction);

    List<Transaction> getInitiatedTransactions(String accountNumber, Date from, Date to);

    List<Transaction> getNotInitiatedTransactions(String accountNumber, Date from, Date to);
}

/*
acc
* */