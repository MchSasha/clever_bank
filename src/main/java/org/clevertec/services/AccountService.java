package org.clevertec.services;

import org.clevertec.domain.Account;
import org.clevertec.domain.Transaction;

public interface AccountService {
    Transaction withdrawMoney(String accountNumber, Integer sum);

    Transaction putMoney(String accountNumber, Integer sum);

    Transaction transferMoney(String senderAccountNumber, String recipientAccountNumber, Integer sum);

    Account getAccount(String accountNumber);
}
