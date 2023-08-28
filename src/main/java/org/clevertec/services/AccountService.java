package org.clevertec.services;

import org.clevertec.domain.Account;
import org.clevertec.domain.Transaction;
import org.clevertec.exceptions.AccountNotFoundException;

import java.sql.Date;
import java.util.List;

public interface AccountService {
    Transaction withdrawMoney(String accountNumber, double sum) throws AccountNotFoundException, InsufficientFundsException;

    Transaction putMoney(String accountNumber, double sum);

    Transaction transferMoney(String senderAccountNumber, String recipientAccountNumber, Integer sum);

    Account getAccount(String accountNumber);

    List<Transaction> getAllTransactions(String accountNumber, Date from, Date to);

    int getAllIncome(String accountNumber, Date from, Date to);

    int getAllWithdrawal(String accountNumber, Date from, Date to);

    int getAccountId(String accountNumber);
}
/*
acc id????
* */