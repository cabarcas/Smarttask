package com.alkemy.educativo.smarttask.modelo;

import com.alkemy.educativo.smarttask.enums.EstadoTarea;
import com.alkemy.educativo.smarttask.enums.TipoTarea;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas de Tarea")
public class TareaTest {

    @Test
    @DisplayName("Una tarea nueva debe tener estado PENDIENTE")
    void nuevaTareaDebeEstarPendiente() {
        TareaNormal tarea = new TareaNormal(1, "Estudiar Java");
        assertEquals(EstadoTarea.PENDIENTE, tarea.getEstado());
    }

    @Test
    @DisplayName("Al marcar completada el estado debe cambiar a COMPLETADA")
    void marcarCompletadaCambiaEstado() {
        TareaNormal tarea = new TareaNormal(1, "Estudiar Java");
        tarea.marcarComoCompletada();
        assertEquals(EstadoTarea.COMPLETADA, tarea.getEstado());
    }

    @Test
    @DisplayName("estaCompletada debe retornar false en tarea nueva")
    void estaCompletadaRetornaFalseEnTareaNueva() {
        TareaNormal tarea = new TareaNormal(1, "Estudiar Java");
        assertFalse(tarea.estaCompletada());
    }

    @Test
    @DisplayName("estaCompletada debe retornar true luego de marcar")
    void estaCompletadaRetornaTrueLuegodeMarcar() {
        TareaNormal tarea = new TareaNormal(1, "Estudiar Java");
        tarea.marcarComoCompletada();
        assertTrue(tarea.estaCompletada());
    }

    @Test
    @DisplayName("getDescripcion debe retornar la descripcion correcta")
    void getDescripcionDebeRetornarDescripcionCorrecta() {
        TareaNormal tarea = new TareaNormal(1, "Estudiar Java");
        assertEquals("Estudiar Java", tarea.getDescripcion());
    }

    @Test
    @DisplayName("Constructor con EstadoTarea debe asignar el estado indicado")
    void constructorConEstadoTareaDebeAsignarEstado() {
        // Creamos una subclase anónima para acceder al constructor protegido de Tarea
        Tarea tarea = new Tarea(1, "Tarea con estado", EstadoTarea.COMPLETADA) {
            @Override
            public TipoTarea getTipo() { return TipoTarea.NORMAL; }
            @Override
            public void ejecutar() {}
            @Override
            public String obtenerResumen() { return ""; }
        };
        assertEquals(EstadoTarea.COMPLETADA, tarea.getEstado());
    }

    @Test
    @DisplayName("Constructor con EstadoTarea PENDIENTE debe mantener estado PENDIENTE")
    void constructorConEstadoPendienteDebeMantenerloPendiente() {
        Tarea tarea = new Tarea(2, "Otra tarea", EstadoTarea.PENDIENTE) {
            @Override
            public TipoTarea getTipo() { return TipoTarea.NORMAL; }
            @Override
            public void ejecutar() {}
            @Override
            public String obtenerResumen() { return ""; }
        };
        assertFalse(tarea.estaCompletada());
    }
}