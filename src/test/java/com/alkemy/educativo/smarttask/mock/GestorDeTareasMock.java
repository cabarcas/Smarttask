package com.alkemy.educativo.smarttask.mock;

import com.alkemy.educativo.smarttask.gestor.GestorDeTareas;
import com.alkemy.educativo.smarttask.modelo.Tarea;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock manual de {@link GestorDeTareas} para uso en pruebas.
 *
 * <p>Permite controlar el comportamiento del gestor sin depender de su
 * implementación real. Útil para probar clases que usan GestorDeTareas
 * como dependencia (por ejemplo, MenuConsola) de forma aislada.
 *
 * <p>Funcionalidades del mock:
 * <ul>
 *   <li>Configurar el resultado de {@code marcarTarea} y {@code eliminarTarea}.</li>
 *   <li>Controlar la lista de tareas devuelta por {@code getTareas}.</li>
 *   <li>Registrar si se llamó a {@code agregarTarea} (espía básico).</li>
 * </ul>
 *
 * <p>Ejemplo de uso:
 * <pre>{@code
 *   GestorDeTareasMock mock = new GestorDeTareasMock();
 *   mock.setResultadoMarcar(true);
 *   mock.setTareasSimuladas(List.of(new TareaNormal(1, "Test")));
 *   // pasar mock a MenuConsola o clase bajo prueba
 * }</pre>
 */
public class GestorDeTareasMock extends GestorDeTareas {

    private boolean resultadoMarcar   = false;
    private boolean resultadoEliminar = false;
    private List<Tarea> tareasSimuladas = new ArrayList<>();
    private boolean agregarFueLlamado = false;
    private Tarea ultimaTareaAgregada = null;
    private int contadorIdSimulado = 0;

    // ── Configuración del mock ─────────────────────────────────────────────

    /** Define qué debe retornar {@code marcarTarea}. */
    public void setResultadoMarcar(boolean resultado)   { this.resultadoMarcar = resultado; }

    /** Define qué debe retornar {@code eliminarTarea}. */
    public void setResultadoEliminar(boolean resultado) { this.resultadoEliminar = resultado; }

    /** Define la lista de tareas que devolverá {@code getTareas}. */
    public void setTareasSimuladas(List<Tarea> tareas)  { this.tareasSimuladas = new ArrayList<>(tareas); }

    // ── Inspección del espía ───────────────────────────────────────────────

    /** Retorna {@code true} si {@code agregarTarea} fue invocado al menos una vez. */
    public boolean agregarFueLlamado()     { return agregarFueLlamado; }

    /** Retorna la última tarea que se pasó a {@code agregarTarea}, o {@code null}. */
    public Tarea getUltimaTareaAgregada() { return ultimaTareaAgregada; }

    // ── Overrides del mock ─────────────────────────────────────────────────

    @Override
    public int generarNuevoId() {
        return ++contadorIdSimulado;
    }

    @Override
    public void agregarTarea(Tarea tarea) {
        agregarFueLlamado = true;
        ultimaTareaAgregada = tarea;
        tareasSimuladas.add(tarea);
    }

    @Override
    public boolean marcarTarea(String id) {
        return resultadoMarcar;
    }

    @Override
    public boolean eliminarTarea(String id) {
        return resultadoEliminar;
    }

    @Override
    public List<Tarea> getTareas() {
        return new ArrayList<>(tareasSimuladas);
    }
}
