package com.alkemy.educativo.smarttask.ui;
import com.alkemy.educativo.smarttask.enums.TipoTarea;
import com.alkemy.educativo.smarttask.factory.TareaFactory;
import com.alkemy.educativo.smarttask.gestor.GestorDeTareas;
import com.alkemy.educativo.smarttask.modelo.Tarea;
import java.util.Scanner;

/**
 * Clase responsable de la interfaz de usuario por consola del sistema SmartTask.
 * Gestiona toda la interacción con el usuario: entradas, validaciones, salidas
 * y navegación del menú. Delega la lógica de negocio a {@link GestorDeTareas}.
 * 
 * <p>Validaciones aplicadas en esta capa:
 * <ul>
 *   <li>Descripción no puede estar vacía al agregar una tarea.</li>
 *   <li>Prioridad debe ser "1" (Normal) o "2" (Urgente); cualquier otro valor
 *       se trata como Normal con aviso al usuario.</li>
 *   <li>No se permite marcar ni eliminar si la lista está vacía.</li>
 *   <li>El ID ingresado no puede ser vacío o en blanco.</li>
 * </ul>
 */
public class MenuConsola {

    private final GestorDeTareas gestor;

    /**
     * Crea el menú vinculado a un gestor de tareas.
     *
     * @param gestor instancia del gestor que maneja la lógica de negocio
     */
    public MenuConsola(GestorDeTareas gestor) {
        this.gestor = gestor;
    }

    /**
     * Inicia el ciclo principal del menú interactivo.
     * Continúa mostrando opciones hasta que el usuario seleccione "Salir".
     */
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        String accion = "";

        do {
            System.out.println("=== SmartTask (Gestor de Tareas) ===");
            System.out.println("1. Agregar Tarea");
            System.out.println("2. Listar Tareas");
            System.out.println("3. Marcar tarea como completada");
            System.out.println("4. Eliminar Tarea");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            accion = scanner.nextLine().trim();

            switch (accion) {
                case "1": agregarTarea(scanner); break;
                case "2": listarTareas();        break;
                case "3": marcarTarea(scanner);  break;
                case "4": eliminarTarea(scanner);break;
                case "5": System.out.println("Saliendo del gestor de tareas."); break;
                default:  System.out.println("Opción inválida. Ingrese un número del 1 al 5.");
            }

        } while (!accion.equals("5"));

        scanner.close();
    }

    /**
     * Solicita descripción y prioridad al usuario, y agrega la nueva tarea al gestor.
     * La descripción no puede estar vacía. Si la prioridad es inválida,
     * se asigna Normal por defecto con aviso al usuario.
     * 
     * @param scanner instancia activa del lector de consola
     */
    private void agregarTarea(Scanner scanner) {
        System.out.print("Ingrese la descripción de la tarea: ");
        String descripcion = scanner.nextLine().trim();
        if(descripcion.isBlank()) {
            System.out.println("La descripción no puede estar vacía. Tarea no creada.");
            return;
        }

        System.out.println("[1] Normal  [2] Urgente");
        System.out.print("Seleccione la prioridad de la tarea: ");
        String prioridad = scanner.nextLine().trim();
        if (!prioridad.equals("1") && !prioridad.equals("2")) {
            System.out.println("Prioridad inválida. Se asignó 'Normal' por defecto.");
            prioridad = "1";
        }

        Tarea nuevaTarea = TareaFactory.crear(gestor.generarNuevoId(), descripcion, prioridad);
        gestor.agregarTarea(nuevaTarea);
        System.out.println("Tarea agregada.");
    }

    /**
     * Imprime en consola todas las tareas registradas y un resumen de conteos.
     */
    private void listarTareas() {
        if (gestor.getTareas().isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
        for (Tarea tarea : gestor.getTareas()) {
            System.out.println(tarea.obtenerResumen());
        }
        contarTareas();
    }

    /**
     * Solicita un ID al usuario y marca la tarea correspondiente como completada.
     * Si la lista está vacía o el ID es vacío, informa al usuario y retorna sin acción.
     *
     * @param scanner instancia activa del lector de consola
     */
    private void marcarTarea(Scanner scanner) {
        if (gestor.getTareas().isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
        listarTareas();
        System.out.print("Ingrese el ID de la tarea a completar: ");
        String id = scanner.nextLine().trim();
        if (id.isBlank()) {
            System.out.println("El ID no puede estar vacío.");
            return;
        }
        if (gestor.marcarTarea(id)) {
            System.out.println("Tarea con ID " + id + " marcada como completada.");
        } else {
            System.out.println("No existe tarea con ID " + id + ".");
        }
    }

    /**
     * Solicita un ID al usuario y elimina la tarea correspondiente.
     * Si la lista está vacía o el ID es vacío, informa al usuario y retorna sin acción.
     *
     * @param scanner instancia activa del lector de consola
     */
    private void eliminarTarea(Scanner scanner) {
        if (gestor.getTareas().isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
        listarTareas();
        System.out.print("Ingrese el ID de la tarea a eliminar: ");
        String id = scanner.nextLine().trim();
        if (id.isBlank()) {
            System.out.println("El ID no puede estar vacío.");
            return;
        }
        if (gestor.eliminarTarea(id)) {
            System.out.println("Tarea con ID " + id + " eliminada.");
        } else {
            System.out.println("No existe tarea con ID " + id + ".");
        }
    }

    /**
     * Imprime un resumen con el conteo de tareas por prioridad y por estado.
     */
    private void contarTareas() {
        int normales = 0, urgentes = 0, pendientes = 0, completadas = 0;

        for (Tarea tarea : gestor.getTareas()) {
            if (tarea.getTipo() == TipoTarea.URGENTE) urgentes++;
            else normales++;

            if (tarea.estaCompletada()) completadas++;
            else pendientes++;
        }

        System.out.println("───────────────────────────────────────────────────────────");
        System.out.println("Normales: " + normales + " | Urgentes: " + urgentes +
                           " || Pendientes: " + pendientes + " | Completadas: " + completadas);
    }
}