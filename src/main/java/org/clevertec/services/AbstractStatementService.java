package org.clevertec.services;

import java.sql.Date;

public interface AbstractStatementService {
    String form(String number, Date from, Date to);
}
