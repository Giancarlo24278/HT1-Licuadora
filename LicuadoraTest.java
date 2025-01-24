package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LicuadoraTest {

    LicuadoraBasica licuadora = new LicuadoraBasica();

    @BeforeEach
    void setUp() {
    
    }

    @Test
    void testEncenderLicuadora() {
        assertTrue(licuadora.encenderLicuadora());
        assertFalse(licuadora.encenderLicuadora(), "La licuadora ya debería estar encendida.");
    }

    @Test
    void testApagarLicuadora() {
        licuadora.encenderLicuadora();
        assertTrue(licuadora.apagarLicuadora());
        assertFalse(licuadora.apagarLicuadora(), "La licuadora ya debería estar apagada.");
    }

    @Test
    void testLlenarLicuadora() {
        assertTrue(licuadora.llenarLicuadora());
        assertFalse(licuadora.llenarLicuadora(), "La licuadora ya debería estar llena.");
    }

    @Test
    void testVaciarLicuadora() {
        licuadora.llenarLicuadora();
        assertTrue(licuadora.vaciarLicuadora());
        assertFalse(licuadora.vaciarLicuadora(), "La licuadora ya debería estar vacía.");
    }

    @Test
    void testIncrementarVelocidad() {
        licuadora.encenderLicuadora();
        licuadora.llenarLicuadora();

        assertTrue(licuadora.incrementarVelocidad(3), "Debería ser posible incrementar la velocidad.");
        assertEquals(3, licuadora.obtenerVelocidadActual());

        assertTrue(licuadora.incrementarVelocidad(2), "Debería ser posible incrementar la velocidad.");
        assertEquals(5, licuadora.obtenerVelocidadActual());

        assertFalse(licuadora.incrementarVelocidad(6), "No debería ser posible superar la velocidad máxima de 10.");
        assertEquals(5, licuadora.obtenerVelocidadActual());
    }

    @Test
    void testObtenerVelocidadActual() {
        assertEquals(0, licuadora.obtenerVelocidadActual(), "La velocidad inicial debería ser 0.");
        licuadora.encenderLicuadora();
        licuadora.llenarLicuadora();
        licuadora.incrementarVelocidad(5);
        assertEquals(5, licuadora.obtenerVelocidadActual(), "La velocidad debería ser 5 después del incremento.");
    }

    @Test
    void testEstaLlena() {
        assertFalse(licuadora.estaLlena(), "La licuadora debería estar vacía inicialmente.");
        licuadora.llenarLicuadora();
        assertTrue(licuadora.estaLlena(), "La licuadora debería estar llena.");
    }
}
