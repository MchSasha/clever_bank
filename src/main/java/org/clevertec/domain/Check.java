package org.clevertec.domain;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Check extends AbstractFinancialDocument{
    public static final String CHECK_FORM = """
                ___________________________________________________\s
                |                 Банковский чек                  |\s
                | Чек:                                 %10d |\s
                | %-10s                             %8s |\s
                | Тип транзакции:                      %10s |\s
                | Банк отправителя:          %20s |\s
                | Банк получателя:           %20s |\s
                | Счет отправителя:  %28s |\s
                | Счет получателя:   %28s |\s
                | Сумма:                           %10.2f %3s |\s
                |_________________________________________________|\s
                """;

    @Override
    public String toString() {
        return CHECK_FORM;
    }
}
