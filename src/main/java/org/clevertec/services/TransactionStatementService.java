package org.clevertec.services;

import org.clevertec.domain.Transaction;

public interface TransactionStatementService extends AbstractStatementService {
    void addStatementRow(String statementInProcess,Transaction transaction);
}
