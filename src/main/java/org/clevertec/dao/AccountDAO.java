package org.clevertec.dao;

import java.sql.Date;

public interface AccountDAO {
    int updateBalance(Integer  accountId, double sum);

    int transferMoney(int senderAccountId, int recipientAccountId, double sum);

    int getAccountId(String accountNumber);

    double getTotalIncome(int accountId, Date from, Date to);

    double getTotalWithdrawal(int accountId, Date from, Date to);
}

/*
* int Int ?????????
* */
