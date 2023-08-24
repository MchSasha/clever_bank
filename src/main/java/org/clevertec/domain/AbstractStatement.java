package org.clevertec.domain;

public class AbstractStatement extends AbstractFinancialDocument{
    public static final String STATEMENT_HEADER_FORM = """
                                                 Выписка                     \s
                                                Clever-Bank                   \s
                 Клиент                        | %s\s
                 Счет                          | %s\s
                 Валюта                        | %s\s
                 Дата открытия                 | %s\s
                 Период                        | %s - %s\s
                 Дата и время формирования     | %s\s
                 Остаток                       | %.2f %s\s
                """;

    @Override
    public String toString() {
        return STATEMENT_HEADER_FORM;
    }
}
