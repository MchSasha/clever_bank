package org.clevertec.services;

import org.clevertec.domain.Transaction;
import org.clevertec.exceptions.AccountNotFoundException;
import org.clevertec.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountServiceImplTest {

    @Test
    void withdrawMoney() {////no acc exc
        Transaction transaction = new AccountServiceImpl().withdrawMoney("MN1234567890123456789012AAAA", 8);

        assertEquals(new Transaction(null,7, null, Date.valueOf(LocalDate.now()), -8.0),
                transaction);
    }
    @Test
    void withdrawMoneyWithNoSuchAccount() {////?????????? -1 id no acc exc
        assertThrows(AccountNotFoundException.class,
                () -> new AccountServiceImpl().withdrawMoney("MN1234567893456789012AAAA", 8));
    }
    @Test
    void withdrawMoneyInsufficientSum() {////?????????? id found but 0 rows inserted
        assertThrows(InsufficientFundsException.class,
                () -> new AccountServiceImpl().withdrawMoney("MN1234567890123456789012AAAA", 1000000));
    }


    @Test
    void putMoney() {
        Transaction transaction = new AccountServiceImpl().putMoney("MN1234567890123456789012AAAA", 200000);

        assertEquals(new Transaction(null,7, null, Date.valueOf(LocalDate.now()), 200000.0),
                transaction);
    }
    @Test
    void putMoneyWithNoSuchAccount() {
        assertThrows(AccountNotFoundException.class,
                () -> new AccountServiceImpl().putMoney("MN1234567893456789012AAAA", 8));
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
    void getTotalIncome() {//2
        assertEquals(12500.0,
                new AccountServiceImpl().getTotalIncome("CD1234567890123456789012AAAA",
                        Date.valueOf(LocalDate.now().minusYears(1)),
                        Date.valueOf(LocalDate.now())));

    }

    @Test
    void getTotalWithdrawal() {
        assertEquals(-11700.0,
                new AccountServiceImpl().getTotalWithdrawal("CD1234567890123456789012AAAA",
                        Date.valueOf(LocalDate.now().minusYears(1)),
                        Date.valueOf(LocalDate.now())));
    }

    @Test
    void getAccountId() {
        assertEquals(2,
                new AccountServiceImpl().getAccountId("CD1234567890123456789012AAAA"));
    }
    @Test
    void getAccountIdWithNoSuchAccount() {
        assertEquals(-1,
                new AccountServiceImpl().getAccountId("CD12345690123456789012AAAA"));
    }
}