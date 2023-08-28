package org.clevertec.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Transaction {
    private Integer transactionId;

    private Integer senderAccountId;

    private Integer recipientAccountId;

    private Date date;

    private Double sum;

    public Transaction(Integer senderAccountId, Integer recipientAccountId, Date date, Double sum) {            //?????????
        this.senderAccountId = senderAccountId;
        this.recipientAccountId = recipientAccountId;
        this.date = date;
        this.sum = sum;
    }

    public Transaction(Integer senderAccountId, Date date, Double sum) {            //?????????
        this.senderAccountId = senderAccountId;
        this.date = date;
        this.sum = sum;
    }


}

