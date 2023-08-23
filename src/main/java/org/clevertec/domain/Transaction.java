package org.clevertec.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Transaction {
    private Integer transactionId;

    private Integer senderAccountId;

    private Integer recipientAccountId;

    private Date date;

    private Integer sum;

    private Integer currencyId;
}

