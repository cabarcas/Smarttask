package com.alkemy.educativo.smarttask.mock;

import com.alkemy.educativo.smarttask.enums.TipoTarea;
import com.alkemy.educativo.smarttask.modelo.TareaNormal;
import com.alkemy.educativo.smarttask.modelo.TareaUrgente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas usando GestorDeTareasMock")
public class GestorDeTareasMockTest {

    private GestorDeTareasMock mock;

    @BeforeEach
    void setUp() {
        mock = new GestorDeTareasMock();
    }

    @Test
    @DisplayName("Mock retorna false para marcarTarea por defecto")
    void mockMarcarPorDefectoEsFalse() {
        assertFalse(mock.marcarTarea("1"));
    }

    @Test
    @DisplayName("Mock retorna true para marcarTarea cuando se configura")
    void mockMarcarConfiguradoRetornaTrue() {
        mock.setResultadoMarcar(true);
        assertTrue(mock.marcarTarea("1"));
    }

    @Test
    @DisplayName("Mock retorna false para eliminarTarea por defecto")
    void mockEliminarPorDefectoEsFalse() {
        assertFalse(mock.eliminarTarea("1"));
    }

    @Test
    @DisplayName("Mock retorna true para eliminarTarea cuando se configura")
    void mockEliminarConfiguradoRetornaTrue() {
        mock.setResultadoEliminar(true);
        assertTrue(mock.eliminarTarea("99"));
    }

    @Test
    @DisplayName("Mock registra que agregarTarea fue llamado")
    void mockRegistraLlamadaAAgregarTarea() {
        assertFalse(mock.agregarFueLlamado());
        mock.agregarTarea(new TareaNormal(1, "Test"));
        assertTrue(mock.agregarFueLlamado());
    }

    @Test
    @DisplayName("Mock registra la última tarea agregada")
    void mockRegistraUltimaTareaAgregada() {
        mock.agregarTarea(new TareaNormal(1, "Test"));
        assertNotNull(mock.getUltimaTareaAgregada());
        assertEquals("Test", mock.getUltimaTareaAgregada().getDescripcion());
    }

    @Test
    @DisplayName("Mock devuelve lista vacía por defecto")
    void mockDevuelveListaVaciaPorDefecto() {
        assertTrue(mock.getTareas().isEmpty());
    }

    @Test
    @DisplayName("Mock devuelve la lista simulada configurada")
    void mockDevuelveListaSimulada() {
        mock.setTareasSimuladas(List.of(
            new TareaNormal(1, "Normal"),
            new TareaUrgente(2, "Urgente")
        ));
        assertEquals(2, mock.getTareas().size());
    }

    @Test
    @DisplayName("Mock generarNuevoId incrementa secuencialmente")
    void mockGenerarNuevoIdIncrementa() {
        assertEquals(1, mock.generarNuevoId());
        assertEquals(2, mock.generarNuevoId());
    }

    @Test
    @DisplayName("La lista devuelta por getTareas es una copia defensiva")
    void mockListaEsDefensiva() {
        mock.setTareasSimuladas(List.of(new TareaNormal(1, "Test")));
        mock.getTareas().clear();
        assertEquals(1, mock.getTareas().size());
    }

    @Test
    @DisplayName("Mock puede simular lista con tarea urgente")
    void mockPuedeSimularTareaUrgente() {
        mock.setTareasSimuladas(List.of(new TareaUrgente(1, "Urgente")));
        assertEquals(TipoTarea.URGENTE, mock.getTareas().get(0).getTipo());
    }
}
