package com.alkemy.educativo.smarttask.gestor;
import com.alkemy.educativo.smarttask.modelo.Tarea;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona la colección de tareas del sistema SmartTask.
 * Contiene exclusivamente la lógica de negocio: agregar, listar,
 * marcar como completada y eliminar tareas.
 *
 * <p>Es responsabilidad de esta clase garantizar la integridad de los datos:
 * no se aceptan tareas nulas ni IDs en blanco.
 */
public class GestorDeTareas {

    private List<Tarea> tareas;
    private int contadorId;

    /**
     * Inicializa el gestor con una lista vacía y el contador de IDs en cero.
     */
    public GestorDeTareas() {
        tareas = new ArrayList<>();
        contadorId = 0;
    }

    /**
     * Genera y devuelve un nuevo ID único, incrementando el contador interno.
     * 
     * @return nuevo ID disponible
     */
    public int generarNuevoId() {
        contadorId++;
        return contadorId;
    }

    /**
     * Agrega una tarea a la lista interna.
     *
     * @param tarea tarea a agregar; no puede ser null
     * @throws IllegalArgumentException si la tarea es null
     */
    public void agregarTarea(Tarea tarea) {
        if (tarea == null) {
            throw new IllegalArgumentException("La tarea no puede ser null.");
        }
        tareas.add(tarea);
    }

    /**
     * Elimina la tarea con el ID especificado.
     *
     * @param id identificador de la tarea a eliminar; no puede ser vacío
     * @return {@code true} si la tarea fue encontrada y eliminada,
     *         {@code false} si no existía ninguna tarea con ese ID
     * @throws IllegalArgumentException si el ID es null o está en blanco
     */
    public boolean eliminarTarea(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El ID no puede ser vacío.");
        }
        return tareas.removeIf(t -> t.getId().equals(id));
    }

    /**
     * Marca como completada la tarea con el ID especificado.
     *
     * @param id identificador de la tarea a marcar; no puede ser vacío
     * @return {@code true} si la tarea fue encontrada y marcada,
     *         {@code false} si no existía ninguna tarea con ese ID
     * @throws IllegalArgumentException si el ID es null o está en blanco
     */
    public boolean marcarTarea(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El ID no puede ser vacío.");
        }
        for (Tarea tarea : tareas) {
            if (tarea.getId().equals(id)) {
                tarea.marcarComoCompletada();
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve la lista de tareas.
     * 
     * @return lista con las tareas actuales
     */
    public List<Tarea> getTareas() {
        return new ArrayList<>(tareas);
    }

    /**
     * Devuelve la tarea del índice especificado.
     *
     * @param indice posición en la lista (base 0)
     * @return tarea en esa posición
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    public Tarea obtenerTarea(int indice) {
        if (indice < 0 || indice >= tareas.size()) {
            throw new IndexOutOfBoundsException(
                "Índice " + indice + " fuera de rango. Tamaño actual: " + tareas.size());
        }
        return tareas.get(indice);
    }
}