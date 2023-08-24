package org.clevertec.services;

import org.clevertec.domain.AbstractStatement;
import org.clevertec.domain.Check;

public interface BankService {
    Check withdrawMoney();

    Check putMoney();

    Check transferMoney();

    AbstractStatement getTransactionStatement();

    AbstractStatement getMoneyStatement();
}
/*
check absSt or absFinDoc ????
* */