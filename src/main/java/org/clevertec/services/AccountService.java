package org.clevertec.services;

import org.clevertec.domain.Account;
import org.clevertec.domain.Transaction;

import java.sql.Date;
import java.util.List;

public interface AccountService {
    Transaction withdrawMoney(String accountNumber, Integer sum);

    Transaction putMoney(String accountNumber, Integer sum);

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