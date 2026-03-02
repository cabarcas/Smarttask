package com.alkemy.educativo.smarttask.modelo;

import com.alkemy.educativo.smarttask.enums.EstadoTarea;
import com.alkemy.educativo.smarttask.enums.TipoTarea;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas de TareaUrgente")
public class TareaUrgenteTest {

    @Test
    @DisplayName("Debe tener tipo URGENTE")
    void debeTenerTipoUrgente() {
        assertEquals(TipoTarea.URGENTE, new TareaUrgente(1, "Test").getTipo());
    }

    @Test
    @DisplayName("Debe nacer con estado PENDIENTE")
    void debeNacerConEstadoPendiente() {
        assertEquals(EstadoTarea.PENDIENTE, new TareaUrgente(1, "Test").getEstado());
    }

    @Test
    @DisplayName("obtenerResumen debe contener prefijo [URGENTE]")
    void obtenerResumenContienePrefijoUrgente() {
        assertTrue(new TareaUrgente(1, "Test").obtenerResumen().contains("[URGENTE]"));
    }

    @Test
    @DisplayName("obtenerResumen contiene la descripción de la tarea")
    void obtenerResumenContieneDescripcion() {
        assertTrue(new TareaUrgente(1, "Revisar").obtenerResumen().contains("Revisar"));
    }

    @Test
    @DisplayName("Tipo URGENTE no debe ser igual a NORMAL")
    void tipoUrgenteNoEsNormal() {
        assertNotEquals(TipoTarea.NORMAL, new TareaUrgente(1, "Test").getTipo());
    }
}
