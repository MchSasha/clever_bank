package org.clevertec.services;

import org.clevertec.domain.Transaction;


public interface TransactionService {
    String getComment(Transaction transaction);

    String getExtendedComment(Transaction transaction);

    void saveTransaction(Transaction transaction);
}
