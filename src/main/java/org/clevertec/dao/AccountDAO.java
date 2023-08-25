package org.clevertec.dao;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public interface AccountDAO {
    int updateBalance(Integer  accountId, double sum) throws IOException, SQLException;

    void transferMoney(String senderAccountNumber, String recipientAccountNumber, double sum);

    int getAccountId(String accountNumber);

    int getAllIncome(String accountNumber, Date from, Date to);

    int getAllWithdrawal(String accountNumber, Date from, Date to);
}

/*
* int Int ?????????
* */
