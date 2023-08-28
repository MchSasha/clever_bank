package org.clevertec.services;

import org.clevertec.domain.Account;
import org.clevertec.domain.Transaction;
import org.clevertec.exceptions.AccountNotFoundException;
import org.clevertec.exceptions.InsufficientFundsException;

import java.sql.Date;
import java.util.List;

public interface AccountService {
    Transaction withdrawMoney(String accountNumber, double sum) throws AccountNotFoundException, InsufficientFundsException;

    Transaction putMoney(String accountNumber, double sum);

    Transaction transferMoney(String senderAccountNumber, String recipientAccountNumber, Integer sum);

    Account getAccount(String accountNumber);

    List<Transaction> getAllTransactions(String accountNumber, Date from, Date to);

    double getTotalIncome(String accountNumber, Date from, Date to);

    double getTotalWithdrawal(String accountNumber, Date from, Date to);

    int getAccountId(String accountNumber);
}
/*
acc id????
* */