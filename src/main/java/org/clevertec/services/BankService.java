package org.clevertec.services;

public interface BankService {
    String withdrawMoney();

    String putMoney();

    String transferMoney();

    String getTransactionStatement();

    String getMoneyStatement();


}
/*
check absSt or absFinDoc ????
для работы сервиса приложения меняю FinancialDocument'ы на Стринги
* */