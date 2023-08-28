package org.clevertec.services;

import org.clevertec.domain.Transaction;
import org.clevertec.exceptions.AccountNotFoundException;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountServiceImplTest {

    @Test
    void withdrawMoney() {////no acc exc
        Transaction transaction = new AccountServiceImpl().withdrawMoney("MN1234567890123456789012AAAA", 8);

        assertEquals(new Transaction(null,7, null, Date.valueOf(LocalDate.now()), -8.0), transaction);
    }
    @Test
    void withdrawMoneyWithNoSuchAccount() {////?????????? -1 id no acc exc
        assertThrows(AccountNotFoundException.class, () -> {
            Transaction transaction = new AccountServiceImpl().withdrawMoney("MN1234567893456789012AAAA", 8);
        });
    }
    @Test
    void withdrawMoneyInsufficientSum() {////?????????? id found but 0 rows inserted
        assertThrows(InsufficientFundsException.class, () -> {
            Transaction transaction = new AccountServiceImpl().withdrawMoney("MN1234567890123456789012AAAA", 100000);
        });
    }


    @Test
    void putMoney() {
    }

    @Test
    void transferMoney() {
    }

    @Test
    void getAccount() {
    }

    @Test
    void getAllTransactions() {
    }

    @Test
    void getAllIncome() {
    }

    @Test
    void getAllWithdrawal() {
    }

    @Test
    void getAccountId() {
    }
}