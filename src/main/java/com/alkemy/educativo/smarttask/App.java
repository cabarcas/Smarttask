package com.alkemy.educativo.smarttask;

import com.alkemy.educativo.smarttask.gestor.GestorDeTareas;
import com.alkemy.educativo.smarttask.ui.MenuConsola;

/**
 * Clase principal de la aplicación SmartTask.
 * Punto de entrada del programa — crea el gestor y lanza el menú interactivo.
 * 
 * <p>Principios SOLID aplicados en el diseño:
 * <ul>
 *   <li><b>S — Single Responsibility:</b> cada clase tiene una única razón para cambiar.
 *       {@code GestorDeTareas} maneja la lógica de negocio; {@code MenuConsola} maneja
 *       la interacción con el usuario.</li>
 *   <li><b>O — Open/Closed:</b> para agregar un nuevo tipo de tarea basta con extender
 *       {@code TareaFactory} sin modificar {@code GestorDeTareas} ni {@code MenuConsola}.</li>
 *   <li><b>L — Liskov Substitution:</b> {@code TareaNormal} y {@code TareaUrgente} pueden
 *       usarse donde se espera {@code Tarea} sin romper el comportamiento del sistema.</li>
 *   <li><b>I — Interface Segregation:</b> {@code Accionable} define solo los contratos
 *       que toda tarea necesita cumplir.</li>
 *   <li><b>D — Dependency Inversion:</b> {@code GestorDeTareas} depende de la abstracción
 *       {@code Tarea}, no de {@code TareaNormal} ni {@code TareaUrgente} directamente.</li>
 * </ul>
 */
public class App {

    /**
     * Constructor privado — impide instanciar la clase.
     * {@code App} es una clase utilitaria que solo contiene el método main.
     */
     private App() {}

     /**
     * Método principal que inicia la aplicación.
     * Crea una instancia del gestor de tareas y lanza el menú interactivo.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        GestorDeTareas gestor = new GestorDeTareas();
        MenuConsola menu = new MenuConsola(gestor);
        menu.iniciar();
    }
}

/* Principios SOLID aplicados en el diseño de la aplicación:
S — Single Responsibility (Responsabilidad Única)
    Cada clase tiene una sola razón para cambiar.
    GestorDeTareas maneja lógica, MenuConsola maneja consola.
O — Open/Closed (Abierto/Cerrado)
    Abierto para extensión, cerrado para modificación.
    TareaFactory centraliza la creación sin tocar GestorDeTareas.
L — Liskov Substitution (Sustitución de Liskov)
    Una subclase debe poder reemplazar a su clase base sin romper el programa.
    1. No restringir lo que la clase base permite.
    2. No cambiar el significado de los métodos heredados.
    3. No lanzar excepciones que la clase base no lanza.
I — Interface Segregation (Segregación de Interfaces)
    Una clase no debería verse obligada a implementar métodos que no usa.
    Accionable define solo lo que toda tarea necesita.
D — Dependency Inversion (Inversión de Dependencias)
    Depender de abstracciones, no de implementaciones concretas.
    GestorDeTareas depende de Tarea, no de TareaNormal ni TareaUrgente.
    TareaFactory centraliza la creación aislando las clases concretas.
*/