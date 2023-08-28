package org.clevertec.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserDAOImplTest {

    @Test
    void getName() {
        String expected = "Зайцев Сергей Алексеевич";

        assertEquals(expected, new UserDAOImpl().getName(15));
    }
    @Test
    void getNameWithNoSuchUser() {
        assertNull(new UserDAOImpl().getName(1545));
    }
}