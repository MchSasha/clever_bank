package org.clevertec.dao;

import java.sql.Date;

public interface AccountDAO {
    int updateBalance(Integer  accountId, double sum);

    int transferMoney(int senderAccountId, int recipientAccountId, double sum);

    int getAccountId(String accountNumber);

    int getTotalIncome(int accountId, Date from, Date to);

    int getTotalWithdrawal(int accountId, Date from, Date to);
}

/*
* int Int ?????????
* */
