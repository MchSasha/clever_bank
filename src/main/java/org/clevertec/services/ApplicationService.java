package org.clevertec.services;

public interface ApplicationService {
    String getMenu(String ... operations);

    String askAccount(String message);

    Double askSum(String message);

    void executeTransfer();

    void executeReplenishment();

    void executeWithdrawal();

    void executeMoneyStatementForming();

    void executeTransactionStatementForming();
}


/*
валидаторы внутрь
пересмотреть название пополнения
* */
