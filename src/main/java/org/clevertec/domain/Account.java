package org.clevertec.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Account {
    private Integer accountId;
    private Integer bankId;
    private Integer userId;
    private String number;
    private Date creationDate;
    private Double balance;
    private Integer currencyId;
}
