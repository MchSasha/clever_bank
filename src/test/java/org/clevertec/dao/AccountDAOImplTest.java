package org.clevertec.dao;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountDAOImplTest {

    @Test
    void updateBalance() {
        AccountDAO accountDAO = new AccountDAOImpl();
        int affRows;
        try {
            affRows = accountDAO.updateBalance(5, 1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, affRows);
    }
    @Test
    void updateBalanceUnsuccessful() {
        AccountDAO accountDAO = new AccountDAOImpl();
        int affRows;
        try {
            affRows = accountDAO.updateBalance(1000, -1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(0, affRows);
    }
    @Test
    void updateBalanceUnsuccessfulInsufficientSum() {
        AccountDAO accountDAO = new AccountDAOImpl();
        int affRows;
        try {
            affRows = accountDAO.updateBalance(4, -1000000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(0, affRows);
    }

    @Test
    void getAccountId() {
        AccountDAO accountDAO = new AccountDAOImpl();
        int id;
        try {
            id = accountDAO.getAccountId( "AB1234567890123456789012AAAA");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, id);
    }
    @Test
    void getAccountIdUnsuccessful() {
        AccountDAO accountDAO = new AccountDAOImpl();
        int id;
        try {
            id = accountDAO.getAccountId( "1234567890123456789012AAAA");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(-1, id);
    }

    @Test
    void getTotalIncome() {
        AccountDAO accountDAO = new AccountDAOImpl();
        double income;
        try {
            income = accountDAO.getTotalIncome(
                    5,
                    Date.valueOf(LocalDate.now().minusDays(15)),
                    Date.valueOf(LocalDate.now()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(300.00, income);
    }

    @Test
    void getTotalWithdrawal() {
        AccountDAO accountDAO = new AccountDAOImpl();
        double withdrawal;
        try {
            withdrawal = accountDAO.getTotalWithdrawal(
                    6,
                    Date.valueOf(LocalDate.now().minusDays(15)),
                    Date.valueOf(LocalDate.now()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(-2300.00, withdrawal);
    }

    @Test
    void transferMoney() {
        AccountDAO accountDAO = new AccountDAOImpl();
        int transfer;
        try {
            transfer = accountDAO.transferMoney(
                    5,
                    6,
                    900
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, transfer);

    }
    @Test
    void transferMoneyInsufficientSum() {
        AccountDAO accountDAO = new AccountDAOImpl();
        int transfer;
        try {
            transfer = accountDAO.transferMoney(
                    5,
                    6,
                    9000000
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(0, transfer);
    }
}