package org.clevertec.services;

import org.clevertec.domain.Transaction;

public interface BankService {
    String withdrawMoney();

    String putMoney();

    String transferMoney();

    String getTransactionStatement();

    String getMoneyStatement();

    Transaction initiateTransaction(String accountNumber, Double sum);

}
/*
check absSt or absFinDoc ????
для работы сервиса приложения меняю FinancialDocument'ы на Стринги
* */