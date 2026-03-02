package com.alkemy.educativo.smarttask.factory;
import com.alkemy.educativo.smarttask.modelo.Tarea;
import com.alkemy.educativo.smarttask.modelo.TareaNormal;
import com.alkemy.educativo.smarttask.modelo.TareaUrgente;

/**
 * Fábrica de tareas del sistema SmartTask.
 * Centraliza la creación de instancias de Tarea según la prioridad indicada.
 *
 * <p> Aplicando el principio Open/Closed — para agregar un nuevo tipo
 * de tarea solo se extiende esta fábrica, sin modificar GestorDeTareas
 * ni MenuConsola.
 */
public class TareaFactory {

    /**
     * Constructor privado — impide instanciar la clase.
     * TareaFactory es una clase utilitaria de solo métodos estáticos.
     */
    private TareaFactory() {}

    /**
     * Crea y devuelve una nueva tarea según la prioridad indicada.
     * Si la prioridad no coincide con ningún tipo conocido,
     * se crea una TareaNormal por defecto.
     * @param id          identificador único de la tarea
     * @param descripcion texto que describe la tarea
     * @param prioridad   cadena que indica el tipo: "2" para Urgente, cualquier otro valor para Normal
     * @return nueva instancia de Tarea del tipo correspondiente
     */
    public static Tarea crear(int id, String descripcion, String prioridad) {
        switch (prioridad) {
            case "2": return new TareaUrgente(id, descripcion);
            default:  return new TareaNormal(id, descripcion);
        }
    }
}