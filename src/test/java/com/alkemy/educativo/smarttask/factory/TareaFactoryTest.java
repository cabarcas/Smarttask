package com.alkemy.educativo.smarttask.factory;

import com.alkemy.educativo.smarttask.modelo.Tarea;
import com.alkemy.educativo.smarttask.enums.TipoTarea;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas de TareaFactory")
public class TareaFactoryTest {

    @Test
    @DisplayName("Prioridad '1' crea TareaNormal")
    void prioridad1CreaTareaNormal() {
        Tarea tarea = TareaFactory.crear(1, "Tarea test", "1");
        assertEquals(TipoTarea.NORMAL, tarea.getTipo());
    }

    @Test
    @DisplayName("Prioridad '2' crea TareaUrgente")
    void prioridad2CreaTareaUrgente() {
        Tarea tarea = TareaFactory.crear(1, "Tarea test", "2");
        assertEquals(TipoTarea.URGENTE, tarea.getTipo());
    }

    @Test
    @DisplayName("Prioridad inválida crea TareaNormal por defecto")
    void prioridadInvalidaCreaTareaNormalPorDefecto() {
        Tarea tarea = TareaFactory.crear(1, "Tarea test", "99");
        assertEquals(TipoTarea.NORMAL, tarea.getTipo());
    }

    @Test
    @DisplayName("La tarea creada no debe ser null")
    void tareaNoDebeSerNull() {
        Tarea tarea = TareaFactory.crear(1, "Tarea test", "1");
        assertNotNull(tarea);
    }
}