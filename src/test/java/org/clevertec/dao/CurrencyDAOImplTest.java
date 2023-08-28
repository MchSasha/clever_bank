package org.clevertec.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyDAOImplTest {

    @Test
    void getName() {
        assertEquals("BYN", new CurrencyDAOImpl().getName(1));

    }
    @Test
    void getNameWithNoSuchCurrency() {
        assertNull(new CurrencyDAOImpl().getName(15));

    }
}