package com.alkemy.educativo.smarttask.modelo;

import com.alkemy.educativo.smarttask.enums.EstadoTarea;
import com.alkemy.educativo.smarttask.enums.TipoTarea;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas de TareaNormal")
public class TareaNormalTest {

    @Test
    @DisplayName("Debe tener tipo NORMAL")
    void debeTenerTipoNormal() {
        assertEquals(TipoTarea.NORMAL, new TareaNormal(1, "Test").getTipo());
    }

    @Test
    @DisplayName("Debe nacer con estado PENDIENTE")
    void debeNacerConEstadoPendiente() {
        assertEquals(EstadoTarea.PENDIENTE, new TareaNormal(1, "Test").getEstado());
    }

    @Test
    @DisplayName("obtenerResumen debe contener prefijo [NORMAL]")
    void obtenerResumenContienePrefijoNormal() {
        assertTrue(new TareaNormal(1, "Test").obtenerResumen().contains("[NORMAL]"));
    }

    @Test
    @DisplayName("obtenerResumen contiene la descripción de la tarea")
    void obtenerResumenContieneDescripcion() {
        assertTrue(new TareaNormal(1, "MiTarea").obtenerResumen().contains("MiTarea"));
    }

    @Test
    @DisplayName("Tipo NORMAL no debe ser igual a URGENTE")
    void tipoNormalNoEsUrgente() {
        assertNotEquals(TipoTarea.URGENTE, new TareaNormal(1, "Test").getTipo());
    }
}