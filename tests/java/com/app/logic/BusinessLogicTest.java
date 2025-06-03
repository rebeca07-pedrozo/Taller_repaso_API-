package com.app.logic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BusinessLogicTest {

    @Test
    void testCalculoCorrecto() {
        BusinessLogic bl = new BusinessLogic();
        int resultado = bl.calcularTotal(10, 5);
        assertEquals(50, resultado);
    }

    @Test
    void testPrecioNegativo() {
        BusinessLogic bl = new BusinessLogic();
        assertThrows(IllegalArgumentException.class, () -> bl.calcularTotal(-10, 5));
    }

    @Test
    void testCantidadNegativa() {
        BusinessLogic bl = new BusinessLogic();
        assertThrows(IllegalArgumentException.class, () -> bl.calcularTotal(10, -5));
    }
}
