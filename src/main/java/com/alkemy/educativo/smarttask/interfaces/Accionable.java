package com.alkemy.educativo.smarttask.interfaces;
/**
 * Interfaz que define las operaciones comunes que puede realizar una tarea.
 * Contrato base que toda tarea del sistema debe cumplir.
 * Cualquier clase que implemente Accionable debe definir cómo se ejecuta
 * y cómo se representa su resumen.
 */
public interface Accionable {
    /**
     * Ejecuta la acción principal de la tarea.
     */
    void ejecutar();

    /**
     * Devuelve un resumen corto de la tarea.
     * @return texto con el resumen de la tarea
     */
    String obtenerResumen();
}
