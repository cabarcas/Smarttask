package com.alkemy.educativo.smarttask.modelo;

import com.alkemy.educativo.smarttask.enums.EstadoTarea;
import com.alkemy.educativo.smarttask.enums.TipoTarea;
import com.alkemy.educativo.smarttask.interfaces.Accionable;

/**
 * Clase base abstracta que representa una tarea en SmartTask.
 * Define los atributos comunes (id, descripción, estado) y el comportamiento
 * compartido por todas las variantes de tarea.
 *
 * <p>No puede instanciarse directamente. Debe usarse a través de
 * {@link TareaNormal} o {@link TareaUrgente}.
 */
public abstract class Tarea implements Accionable {

    private final int id;
    private final String descripcion;
    private EstadoTarea estado; 

    /**
     * Crea una tarea con estado inicial {@link EstadoTarea#PENDIENTE}.
     *
     * @param id          identificador único de la tarea
     * @param descripcion texto que describe la tarea
     */
    public Tarea(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = EstadoTarea.PENDIENTE;
    }

    /**
     * Crea una tarea con un estado inicial específico.
     *
     * @param id          identificador único de la tarea
     * @param descripcion texto que describe la tarea
     * @param estado      estado inicial de la tarea
     */
    public Tarea(int id, String descripcion, EstadoTarea estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado; 
    }

    /**
     * Devuelve el identificador único de la tarea como {@code String}.
     *
     * @return id de la tarea convertido a texto
     */
    public String getId() { return String.valueOf(id); }

    /**
     * Devuelve la descripción de la tarea.
     *
     * @return texto descriptivo de la tarea
     */
    public String getDescripcion() { return descripcion; }

    /**
     * Devuelve el estado actual de la tarea.
     *
     * @return estado actual ({@link EstadoTarea#PENDIENTE} o {@link EstadoTarea#COMPLETADA})
     */
    public EstadoTarea getEstado() { return estado; }

    /**
    * Devuelve el tipo de tarea.
    * Cada subclase define su propio tipo.
    * @return tipo de la tarea
    */
    public abstract TipoTarea getTipo();

    /**
     * Indica si la tarea fue completada.
     *
     * @return {@code true} si el estado es {@link EstadoTarea#COMPLETADA},
     *         {@code false} en caso contrario
     */
    public boolean estaCompletada() { return estado == EstadoTarea.COMPLETADA; }

    /**
     * Cambia el estado de la tarea a {@link EstadoTarea#COMPLETADA}.
     */
    public void marcarComoCompletada() { this.estado = EstadoTarea.COMPLETADA; }

    /**
     * Representa la tarea como texto con símbolo de estado, ID, descripción y estado.
     * Ejemplo: {@code [X] Tarea 1: Estudiar Java | Completada}
     *
     * @return representación textual de la tarea
     */
    @Override
    public String toString() {
        String simboloEstado = estado == EstadoTarea.COMPLETADA ? "[X]" : "[ ]";
        return simboloEstado + " Tarea " + id + ": " + descripcion +
               " | " + estado.getDescripcion();
    }

    /**
     * Ejecuta la acción de la tarea.
     * Cada subclase define su propio comportamiento.
     * 
     * Al ser un método abstracto, obliga a las subclases a implementar su propia lógica de ejecución,
     * escribiendo "abstract" el compilador no arroja excepción.
     */

    @Override
    public abstract void ejecutar();
}
