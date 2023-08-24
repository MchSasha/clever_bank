package org.clevertec.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class TransactionStatement extends AbstractStatement {
    public static final String TRANSACTION_STATEMENT_HEADERS_FORM = """
                    Дата    |               Примечание                  |   Сумма\s
                --------------------------------------------------------------------\s
                """;

    public static final String TRANSACTION_STATEMENT_ROW_FORM = """
            %-10s  | %-40s  | %.2f %s\s
            """;

    @Override
    public String toString() {
        return super.toString() + TRANSACTION_STATEMENT_HEADERS_FORM;
    }

}
