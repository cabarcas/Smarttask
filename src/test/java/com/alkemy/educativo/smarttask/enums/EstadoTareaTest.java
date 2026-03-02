package com.alkemy.educativo.smarttask.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas de EstadoTarea")
public class EstadoTareaTest {

    @Test
    @DisplayName("PENDIENTE debe tener nivel 0")
    void pendienteDebeTenerNivel0() {
        assertEquals(0, EstadoTarea.PENDIENTE.getNivel());
    }

    @Test
    @DisplayName("COMPLETADA debe tener nivel 1")
    void completadaDebeTenerNivel1() {
        assertEquals(1, EstadoTarea.COMPLETADA.getNivel());
    }

    @Test
    @DisplayName("PENDIENTE debe tener descripcion correcta")
    void pendienteDebeTenerDescripcionCorrecta() {
        assertEquals("Pendiente", EstadoTarea.PENDIENTE.getDescripcion());
    }

    @Test
    @DisplayName("COMPLETADA debe tener descripcion correcta")
    void completadaDebeTenerDescripcionCorrecta() {
        assertEquals("Completada", EstadoTarea.COMPLETADA.getDescripcion());
    }
}