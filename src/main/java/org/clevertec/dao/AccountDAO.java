package org.clevertec.dao;

import java.sql.Date;

public interface AccountDAO {
    void updateBalance(String accountNumber, int sum);

    void transferMoney(String senderAccountNumber, String recipientAccountNumber, int sum);

    int getAccountId(String accountNumber);

    int getAllIncome(String accountNumber, Date from, Date to);

    int getAllWithdrawal(String accountNumber, Date from, Date to);
}

/*
* int Int ?????????
* */
