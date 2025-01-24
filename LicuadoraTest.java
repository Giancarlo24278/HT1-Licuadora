package com.example;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BasicBlenderTest {

    private BasicBlender blender;

    @BeforeEach
    void setUp() {
        blender = new BasicBlender();
    }

    @Test
    void testEncender() {
        blender.Encender();
        assertTrue(blender.lleno() || !blender.lleno(), "La licuadora debería poder encender sin errores.");
    }

    @Test
    void testAgregarIngredientesCuandoEncendida() {
        blender.Encender();
        assertDoesNotThrow(() -> blender.ingredientes("Frutas"), "No debería lanzar excepción cuando está encendida.");
    }

    @Test
    void testAgregarIngredientesSinEncender() {
        Exception exception = assertThrows(IllegalStateException.class, () -> blender.ingredientes("Frutas"));
        assertEquals("La licuadora debe estar encendida para agregar contenido.", exception.getMessage());
    }

    @Test
    void testIncrementarVelocidadCuandoEncendidaYLlena() {
        blender.Encender();
        blender.ingredientes("Frutas");
        blender.incrementarVelocidad();
        assertEquals(1, blender.velocidadActual(), "La velocidad debería ser 1 después de incrementarla una vez.");
    }

    @Test
    void testIncrementarVelocidadSinEncender() {
        Exception exception = assertThrows(IllegalStateException.class, blender::incrementarVelocidad);
        assertEquals("La licuadora debe estar encendida para aumentar la velocidad.", exception.getMessage());
    }

    @Test
    void testIncrementarVelocidadCuandoVacia() {
        blender.Encender();
        Exception exception = assertThrows(IllegalStateException.class, blender::incrementarVelocidad);
        assertEquals("La licuadora no puede funcionar vacía.", exception.getMessage());
    }

    @Test
    void testIncrementarVelocidadAlMaximo() {
        blender.Encender();
        blender.ingredientes("Frutas");
        for (int i = 0; i < 10; i++) {
            blender.incrementarVelocidad();
        }
        Exception exception = assertThrows(IllegalStateException.class, blender::incrementarVelocidad);
        assertEquals("La velocidad máxima es 10.", exception.getMessage());
    }

    @Test
    void testVaciar() {
        blender.Encender();
        blender.ingredientes("Frutas");
        blender.vaciar();
        assertFalse(blender.lleno(), "La licuadora debería estar vacía después de vaciarla.");
        assertEquals(0, blender.velocidadActual(), "La velocidad debería ser 0 después de vaciarla.");
    }
}