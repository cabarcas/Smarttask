package com.alkemy.educativo.smarttask.modelo;

import com.alkemy.educativo.smarttask.enums.TipoTarea;

/**
 * Representa una tarea urgente dentro del sistema SmartTask.
 * Hereda de Tarea y define un comportamiento de alta prioridad al ejecutarse.
 * Se crea con estado PENDIENTE por defecto.
 */
public class TareaUrgente extends Tarea {

    /**
     * Crea una tarea urgente con descripción y estado PENDIENTE por defecto.
     * @param id          identificador único de la tarea
     * @param descripcion texto que describe la tarea
     */
    public TareaUrgente(int id, String descripcion) {
        super(id, descripcion);
    }

    /**
     * Ejecuta la tarea urgente con una advertencia visual en consola.
     * Sobreescribe el comportamiento base de Tarea.
     */
    @Override
    public void ejecutar() {
        System.out.println("!! URGENTE: " + getDescripcion() + " - requiere atención inmediata");
    }

    /**
     * Devuelve un resumen identificando explícitamente el tipo urgente.
     * @return resumen de la tarea urgente
     */
    @Override
    public String obtenerResumen() {
        return "[URGENTE] " + super.toString();
    }

    /**
     * Devuelve el tipo de esta tarea como URGENTE.
     * @return TipoTarea.URGENTE
     */
    @Override
    public TipoTarea getTipo() { return TipoTarea.URGENTE; }
}