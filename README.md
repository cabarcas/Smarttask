# SmartTask — Gestor de Tareas por Consola

SmartTask es una aplicación de consola en Java para gestionar tareas personales. Permite agregar, listar, marcar como completadas y eliminar tareas, diferenciando entre tareas normales y urgentes.

---

## Estructura del proyecto

```
smarttask/
├── src/
│   ├── main/java/com/alkemy/educativo/smarttask/
│   │   ├── App.java                        # Punto de entrada
│   │   ├── enums/
│   │   │   ├── EstadoTarea.java            # PENDIENTE / COMPLETADA
│   │   │   └── TipoTarea.java              # NORMAL / URGENTE
│   │   ├── factory/
│   │   │   └── TareaFactory.java           # Patrón Factory para crear tareas
│   │   ├── gestor/
│   │   │   └── GestorDeTareas.java         # Lógica de negocio
│   │   ├── interfaces/
│   │   │   └── Accionable.java             # Contrato base de toda tarea
│   │   ├── modelo/
│   │   │   ├── Tarea.java                  # Clase abstracta base
│   │   │   ├── TareaNormal.java            # Tarea de prioridad normal
│   │   │   └── TareaUrgente.java           # Tarea de alta prioridad
│   │   └── ui/
│   │       └── MenuConsola.java            # Interfaz de usuario por consola
│   └── test/java/com/alkemy/educativo/smarttask/
│       ├── enums/EstadoTareaTest.java
│       ├── factory/TareaFactoryTest.java
│       ├── gestor/GestorDeTareasTest.java
│       ├── mock/
│       │   ├── GestorDeTareasMock.java     # Mock manual para pruebas
│       │   └── GestorDeTareasMockTest.java
│       └── modelo/
│           ├── TareaTest.java
│           ├── TareaNormalTest.java
│           └── TareaUrgenteTest.java
├── pom.xml
└── README.md
```

---

## Requisitos

- Java 21 o superior
- Maven 3.8 o superior

---

## Cómo compilar y ejecutar

### Compilar
```bash
mvn compile
```

### Generar Javadoc

| Situación                         | Comando suficiente                      |
|-----------------------------------|-----------------------------------------|
| Sin paquetes, todo en una carpeta | `javadoc -d docs *.java`                |
|                                   | `javadoc -d docs smarttask/src/*.java`  |
| Un nivel de paquete               | `javadoc -d docs src/*.java`            |
| Paquetes anidados (este proyecto) | Requiere `-subpackages` o `find`        |

Dado que el proyecto usa paquetes anidados, el comando utilizado es:
```bash
mvn javadoc:javadoc
```

La documentación se genera en `target/reports/apidocs/index.html`.

### Ejecutar tests
```bash
mvn test
```

### Ver reporte de cobertura (JaCoCo)
```bash
mvn test
# Abrir: target/site/jacoco/index.html
```

### Generar JAR ejecutable
```bash
mvn package
```
El JAR se genera en `target/smarttask-1.0-SNAPSHOT.jar`.

Para ejecutarlo necesitás agregar el plugin `maven-jar-plugin` con la clase principal configurada en el `pom.xml` (ver sección "Generar JAR" más abajo).

### Ejecutar el JAR
```bash
java -jar target/smarttask-1.0-SNAPSHOT.jar
```

---

## Generar JAR ejecutable (configuración pom.xml)

Agregar dentro de `<plugins>` en el `pom.xml`:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <version>3.3.0</version>
    <configuration>
        <archive>
            <manifest>
                <mainClass>com.alkemy.educativo.smarttask.App</mainClass>
            </manifest>
        </archive>
    </configuration>
</plugin>
```

Luego ejecutar:
```bash
mvn package
java -jar target/smarttask-1.0-SNAPSHOT.jar
```

---

## Funcionalidades del menú

| Opción | Acción |
|--------|--------|
| 1 | Agregar tarea (Normal o Urgente) |
| 2 | Listar todas las tareas con conteo por tipo y estado |
| 3 | Marcar una tarea como completada por ID |
| 4 | Eliminar una tarea por ID |
| 5 | Salir |

---

## Validaciones implementadas

- La descripción al agregar una tarea no puede estar vacía.
- La prioridad debe ser `1` (Normal) o `2` (Urgente); cualquier otro valor usa Normal por defecto.
- No se puede marcar ni eliminar si la lista está vacía.
- El ID ingresado no puede ser vacío o en blanco.
- `GestorDeTareas` rechaza tareas `null` y IDs vacíos o nulos lanzando `IllegalArgumentException`.
- `obtenerTarea` lanza `IndexOutOfBoundsException` si el índice está fuera de rango.

---

## Principios SOLID aplicados

| Principio | Aplicación |
|-----------|------------|
| **S** — Single Responsibility | `GestorDeTareas` maneja lógica; `MenuConsola` maneja UI |
| **O** — Open/Closed | Nuevo tipo de tarea → extender `TareaFactory`, sin tocar el resto |
| **L** — Liskov Substitution | `TareaNormal` y `TareaUrgente` son intercambiables donde se espera `Tarea` |
| **I** — Interface Segregation | `Accionable` define solo lo estrictamente necesario |
| **D** — Dependency Inversion | `GestorDeTareas` depende de `Tarea` (abstracción), no de subclases concretas |

---

## Patrones de diseño utilizados

- **Factory Method** (`TareaFactory`): centraliza la creación de tareas según prioridad.
- **Template Method** (herencia de `Tarea`): define la estructura general y deja que las subclases implementen el comportamiento específico.

---

## Tests y cobertura

Los tests están escritos con JUnit 5. El proyecto incluye:
- Tests unitarios por clase de negocio.
- Mock manual `GestorDeTareasMock` que permite probar dependencias de forma aislada.
- Cobertura medida con JaCoCo (excluye `App` y `MenuConsola` por ser capa de UI).

# SmartTask — Pseudocódigo y Diagramas de Flujo

---

## 1. Flujo principal — `App.main()`

```
INICIO
  gestor  ← nuevo GestorDeTareas()
  menu    ← nuevo MenuConsola(gestor)
  menu.iniciar()
FIN
```

---

## 2. Ciclo del menú — `MenuConsola.iniciar()`

```
accion ← ""

REPETIR
  mostrar opciones del menú (1-5)
  leer accion

  SEGÚN accion:
    "1" → agregarTarea()
    "2" → listarTareas()
    "3" → marcarTarea()
    "4" → eliminarTarea()
    "5" → mostrar "Saliendo"
    otro → mostrar "Opción inválida"

HASTA QUE accion == "5"
```

---

## 3. Agregar tarea — `agregarTarea()`

```
INICIO agregarTarea
  leer descripcion (trim)

  SI descripcion está vacía:
    mostrar "La descripción no puede estar vacía"
    RETORNAR  ← salida temprana, no se genera ID

  leer prioridad (trim)

  SI prioridad ≠ "1" Y prioridad ≠ "2":
    mostrar "Prioridad inválida. Se asignó Normal"
    prioridad ← "1"

  id       ← gestor.generarNuevoId()   ← solo si llegó hasta acá
  tarea    ← TareaFactory.crear(id, descripcion, prioridad)
  gestor.agregarTarea(tarea)
  mostrar "Tarea agregada: " + tarea.obtenerResumen()
FIN agregarTarea
```

```
┌─────────────────────────────────────────┐
│           AGREGAR TAREA                 │
└──────────────────┬──────────────────────┘
                   │
         leer descripcion
                   │
         ┌─────────▼────────┐
         │ descripcion vacía?│
         └──┬────────────┬───┘
           SÍ            NO
            │             │
     mostrar error   leer prioridad
     RETORNAR             │
                  ┌───────▼──────────┐
                  │prioridad 1 o 2?  │
                  └──┬───────────┬───┘
                    NO           SÍ
                     │            │
              avisar,          usar prioridad
              prioridad="1"       │
                     └──────┬─────┘
                            │
                   generar ID (solo aquí)
                            │
                   crear tarea vía Factory
                            │
                   agregar al gestor
                            │
                   mostrar confirmación
```

---

## 4. Listar tareas — `listarTareas()`

```
INICIO listarTareas
  SI lista está vacía:
    mostrar "No hay tareas registradas"
    RETORNAR

  PARA CADA tarea EN gestor.getTareas():
    mostrar tarea.obtenerResumen()

  contarTareas()
FIN listarTareas
```

---

## 5. Marcar tarea — `marcarTarea()`

```
INICIO marcarTarea
  SI lista vacía:
    mostrar "No hay tareas registradas"
    RETORNAR

  listarTareas()
  leer id (trim)

  SI id está vacío:
    mostrar "El ID no puede estar vacío"
    RETORNAR

  resultado ← gestor.marcarTarea(id)

  SI resultado == true:
    mostrar "Tarea " + id + " marcada como completada"
  SINO:
    mostrar "No existe tarea con ID " + id
FIN marcarTarea
```

---

## 6. Eliminar tarea — `eliminarTarea()`

```
INICIO eliminarTarea
  SI lista vacía:
    mostrar "No hay tareas registradas"
    RETORNAR

  listarTareas()
  leer id (trim)

  SI id está vacío:
    mostrar "El ID no puede estar vacío"
    RETORNAR

  resultado ← gestor.eliminarTarea(id)

  SI resultado == true:
    mostrar "Tarea " + id + " eliminada"
  SINO:
    mostrar "No existe tarea con ID " + id
FIN eliminarTarea
```

---

## 7. TareaFactory — `crear(id, descripcion, prioridad)`

```
INICIO crear(id, descripcion, prioridad)
  SEGÚN prioridad:
    "2" → RETORNAR nuevo TareaUrgente(id, descripcion)
    otro → RETORNAR nuevo TareaNormal(id, descripcion)
FIN crear
```

---

## 8. GestorDeTareas — operaciones clave

### agregarTarea
```
SI tarea == null → lanzar IllegalArgumentException
agregar tarea a la lista interna
```

### eliminarTarea
```
SI id == null O vacío → lanzar IllegalArgumentException
eliminar de la lista el elemento donde tarea.getId() == id
RETORNAR true si fue eliminado, false si no existía
```

### marcarTarea
```
SI id == null O vacío → lanzar IllegalArgumentException
PARA CADA tarea EN lista:
  SI tarea.getId() == id:
    tarea.marcarComoCompletada()
    RETORNAR true
RETORNAR false
```

---

## 9. Ciclo de vida de una tarea

```
Creación
   │
   ▼
Estado: PENDIENTE  ──────────────────────────────┐
   │                                              │
   │  marcarComoCompletada()                      │
   ▼                                              │
Estado: COMPLETADA  (estado final, no reversible) │
   │                                              │
   └─── eliminarTarea() puede ejecutarse en cualquier estado
```