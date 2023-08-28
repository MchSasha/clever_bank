package org.clevertec.services;

import org.clevertec.dao.AccountDAO;
import org.clevertec.dao.AccountDAOImpl;
import org.clevertec.dao.TransactionDAO;
import org.clevertec.dao.TransactionDAOImpl;
import org.clevertec.domain.Account;
import org.clevertec.domain.Transaction;
import org.clevertec.exceptions.AccountNotFoundException;
import org.clevertec.exceptions.InsufficientFundsException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class AccountServiceImpl implements AccountService{

    AccountDAO accountDAO = new AccountDAOImpl();
    TransactionDAO transactionDAO = new TransactionDAOImpl();

    @Override
    public Transaction withdrawMoney(String accountNumber, double sum) throws AccountNotFoundException, InsufficientFundsException {         //int?                //7 acc test
        int accountId = accountDAO.getAccountId(accountNumber);                 // -1 -- no such account //вынести и кидать нет акка, потому что ели он есть и нет вставки, то нет денег
        if(accountId == -1) throw new AccountNotFoundException("Account with number " + accountNumber + " not found");

        Transaction transaction = new Transaction(accountId, Date.valueOf(LocalDate.now()), -sum);

        int rowsAffected = accountDAO.updateBalance(accountId, -sum);           // 0 -- insufficient sum
        if(rowsAffected == 0) throw new InsufficientFundsException("Insufficient funds");

        transactionDAO.saveTransaction(transaction);

        return transaction;
    }

    @Override
    public Transaction putMoney(String accountNumber, double sum) {
        int accountId = accountDAO.getAccountId(accountNumber);                 // -1 -- no such account //вынести и кидать нет акка, потому что ели он есть и нет вставки, то нет денег
        if(accountId == -1) throw new AccountNotFoundException("Account with number " + accountNumber + " not found");

        Transaction transaction = new Transaction(accountId, Date.valueOf(LocalDate.now()), sum);

        accountDAO.updateBalance(accountId, sum);
        transactionDAO.saveTransaction(transaction);

        return transaction;
    }

    @Override
    public Transaction transferMoney(String senderAccountNumber, String recipientAccountNumber, Integer sum) {
        return null;
    }

    @Override
    public Account getAccount(String accountNumber) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions(String accountNumber, Date from, Date to) {
        return null;
    }

    @Override
    public double getTotalIncome(String accountNumber, Date from, Date to) {
        return accountDAO.getTotalIncome(getAccountId(accountNumber), from, to);
    }

    @Override
    public double getTotalWithdrawal(String accountNumber, Date from, Date to) {
        return accountDAO.getTotalWithdrawal(getAccountId(accountNumber), from, to);
    }

    @Override
    public int getAccountId(String accountNumber) {
        return accountDAO.getAccountId(accountNumber);
    }

}

//еще метод для снять/положить
//вынести получение ид
//защита при операциях с бд подряд

