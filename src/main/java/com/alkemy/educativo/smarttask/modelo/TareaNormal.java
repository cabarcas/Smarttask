package com.alkemy.educativo.smarttask.modelo;

import com.alkemy.educativo.smarttask.enums.TipoTarea;

/**
 * Representa una tarea de tipo normal dentro del sistema SmartTask.
 * Hereda de Tarea y define un comportamiento estándar al ejecutarse.
 * Se crea por defecto con prioridad NORMAL.
 */
public class TareaNormal extends Tarea {

    /**
     * Crea una tarea normal con identificador y descripción.
     * @param id          identificador único de la tarea
     * @param descripcion texto que describe la tarea
     */
    public TareaNormal(int id, String descripcion) {
        super(id, descripcion);
    }

    /**
     * Ejecuta la tarea normal informando al usuario por consola.
     * Sobreescribe el comportamiento base de Tarea.
     */
    @Override
    public void ejecutar() {
        System.out.println("Ejecutando tarea normal: " + getDescripcion());
    }

    /**
     * Devuelve un resumen identificando explícitamente el tipo de tarea.
     * @return resumen de la tarea normal
     */
    @Override
    public String obtenerResumen() {
        return "[NORMAL] " + super.toString();
    }

    /**
     * Devuelve el tipo de esta tarea como NORMAL.
     * @return TipoTarea.NORMAL
     */
    @Override
    public TipoTarea getTipo() { return TipoTarea.NORMAL; }
}