package org.clevertec.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class MoneyStatement extends AbstractStatement {
    public static final String MONEY_STATEMENT_TABLE_FORM = """
                        Приход      |       Уход\s
                -----------------------------------------\s
                      %8.2f %3s  |  %8.2f %3s\s
                """;

    @Override
    public String toString() {
        return super.toString() + MONEY_STATEMENT_TABLE_FORM;
    }
}
