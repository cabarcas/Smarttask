package com.alkemy.educativo.smarttask.gestor;

import com.alkemy.educativo.smarttask.modelo.TareaNormal;
import com.alkemy.educativo.smarttask.modelo.TareaUrgente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas de GestorDeTareas")
public class GestorDeTareasTest {

    private GestorDeTareas gestor;

    @BeforeEach
    void setUp() {
        gestor = new GestorDeTareas();
    }

    // ── agregar ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Agregar tarea normal incrementa la lista")
    void agregarTareaIncrementaLista() {
        gestor.agregarTarea(new TareaNormal(gestor.generarNuevoId(), "Tarea 1"));
        assertEquals(1, gestor.getTareas().size());
    }

    @Test
    @DisplayName("Agregar tarea urgente también incrementa la lista")
    void agregarTareaUrgenteIncrementaLista() {
        gestor.agregarTarea(new TareaUrgente(gestor.generarNuevoId(), "Urgente 1"));
        assertEquals(1, gestor.getTareas().size());
    }

    @Test
    @DisplayName("Agregar tarea null lanza IllegalArgumentException")
    void agregarTareaNullLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> gestor.agregarTarea(null));
    }

    // ── eliminar ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Eliminar tarea existente retorna true")
    void eliminarTareaExistenteRetornaTrue() {
        int id = gestor.generarNuevoId();
        gestor.agregarTarea(new TareaNormal(id, "Tarea 1"));
        assertTrue(gestor.eliminarTarea(String.valueOf(id)));
    }

    @Test
    @DisplayName("Eliminar tarea inexistente retorna false")
    void eliminarTareaInexistenteRetornaFalse() {
        assertFalse(gestor.eliminarTarea("999"));
    }

    @Test
    @DisplayName("Eliminar con ID vacío lanza IllegalArgumentException")
    void eliminarConIdVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> gestor.eliminarTarea(""));
    }

    @Test
    @DisplayName("Eliminar con ID null lanza IllegalArgumentException")
    void eliminarConIdNullLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> gestor.eliminarTarea(null));
    }

    // ── marcar ────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Marcar tarea existente retorna true")
    void marcarTareaExistenteRetornaTrue() {
        int id = gestor.generarNuevoId();
        gestor.agregarTarea(new TareaNormal(id, "Tarea 1"));
        assertTrue(gestor.marcarTarea(String.valueOf(id)));
    }

    @Test
    @DisplayName("Marcar tarea inexistente retorna false")
    void marcarTareaInexistenteRetornaFalse() {
        assertFalse(gestor.marcarTarea("999"));
    }

    @Test
    @DisplayName("Marcar con ID vacío lanza IllegalArgumentException")
    void marcarConIdVacioLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> gestor.marcarTarea("  "));
    }

    @Test
    @DisplayName("Marcar con ID null lanza IllegalArgumentException")
    void marcarConIdNullLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> gestor.marcarTarea(null));
    }

    // ── lista defensiva ───────────────────────────────────────────────────────

    @Test
    @DisplayName("La lista devuelta no debe modificar el estado interno")
    void listaDevueltaEsDefensiva() {
        gestor.agregarTarea(new TareaNormal(gestor.generarNuevoId(), "Tarea 1"));
        gestor.getTareas().clear();
        assertEquals(1, gestor.getTareas().size());
    }

    // ── ID ────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("generarNuevoId incrementa secuencialmente")
    void generarNuevoIdIncrementaSecuencialmente() {
        assertEquals(1, gestor.generarNuevoId());
        assertEquals(2, gestor.generarNuevoId());
        assertEquals(3, gestor.generarNuevoId());
    }

    // ── obtenerTarea ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("obtenerTarea con índice válido devuelve la tarea correcta")
    void obtenerTareaConIndiceValido() {
        gestor.agregarTarea(new TareaNormal(gestor.generarNuevoId(), "Primera"));
        assertEquals("Primera", gestor.obtenerTarea(0).getDescripcion());
    }

    @Test
    @DisplayName("obtenerTarea con índice fuera de rango lanza IndexOutOfBoundsException")
    void obtenerTareaConIndiceFueraDeRango() {
        assertThrows(IndexOutOfBoundsException.class, () -> gestor.obtenerTarea(0));
    }
}
