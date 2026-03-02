package com.alkemy.educativo.smarttask.enums;
/**
 * Enum que representa los posibles estados de una tarea.
 */

public enum EstadoTarea {
        
    /** La tarea aún no ha sido completada. Estado inicial por defecto. */
    PENDIENTE(0, "Pendiente"),

    /** La tarea fue finalizada exitosamente. */
    COMPLETADA(1, "Completada");

    private final int nivel;
    private final String descripcion;

    /**
     * Constructor del enum EstadoTarea.
     * @param nivel      valor numérico que representa el estado
     * @param descripcion texto legible del estado
     */
    EstadoTarea(int nivel, String descripcion) {
        this.nivel = nivel;
        this.descripcion= descripcion;
    }

    /**
     * Devuelve el valor numérico del estado.
     * @return nivel del estado
     */
    public int getNivel() { return nivel; }
    
    /**
     * Devuelve el texto legible del estado.
     * @return descripción del estado
     */
    public String getDescripcion() { return descripcion; }
}
