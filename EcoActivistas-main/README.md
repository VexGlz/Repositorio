# Proyecto Base - Gestión de Clientes, Activistas y Problemas

> **Estado:** Código base (NO terminado). Esta repo contiene la estructura inicial y formularios (FRM) — los alumnos deben completar módulos indicados más abajo.

---

## Resumen
Este proyecto es la base de una aplicación de escritorio en Java (Swing) que maneja **Clientes**, **Activistas** y **Problemas**. Dentro del proyecto hay un paquete `views` donde deben ubicarse los formularios (JFrame / JPanel).

> **Importante:** El módulo de **Problemas** no está finalizado. 
> Se agregó el campo `descripcion` a la tabla `Problema`, pero falta yerminar la pantalla/funcionalidad.
> Ademas agrega una pantalla para asignar activistas a problemas (relación N:N). 
> Por ultimo deben terminar la administración de activistas.

---

## Estructura requerida (paquetes / formularios)
Dentro de `src/main/java/<tu/paquete>/views` **crear** los formularios (FRM):

- `FrmPrincipal.java` — JFrame principal. Debe contener:
  - Un `MenuBar` con las opciones del sistema.
  - Un panel central llamado `pnlContenido` donde se cargan/transparentan las demás pantallas.

- `FrmClientes.java` — Formulario completo (CRUD) para clientes.

- `FrmActivistas.java` — Formulario completo (CRUD) para activistas. **Tarea:** implementar la administración completa (crear, leer, actualizar, eliminar).

- `FrmProblemas.java` — Formulario para problemas. **Estado:** incompleto. Contiene ya el campo `descripcion` pero debe completarse con validaciones y con la pantalla para asignar activistas.

> Nota de diseño: Usar `JFrame` o `JPanel` según convenga (por ejemplo, pantallas internas como `JPanel` que se insertan en `pnlContenido`).

---

## Tareas para los alumnos (entregable)
1. **Administración de Activistas** (CRUD) — completar funcionalidad, validaciones y persistencia.
2. **Completar módulo de Problemas**:
   - Terminar las operaciones CRUD.
   - Asegurar que el campo `descripcion` se guarde y muestre correctamente.
3. **Agregar asignación de Activistas a Problemas (pantalla faltante)**:
   - Diseñar una UI donde se pueda asignar/desasignar varios activistas a un problema.
   - Implementar la persistencia de la relación N:N (tabla intermedia).
   - Mostrar, en `FrmProblemas`, la lista de activistas asignados y permitir su gestión.

**Puntos clave:** Un `Problema` puede trabajarse por **varios activistas** y un `Activista` puede trabajar en **varios problemas**.

---

## Configuración de la base de datos

1. Crear un archivo `db.properties` en `src/main/resources` con sus credenciales. Ejemplo:

```
# src/main/resources/db.properties
db.url=jdbc:sqlserver://localhost:1433;databaseName=nombre_db
db.user=tu_usuario
db.password=tu_password
db.driver=com....etc
```

2. Asegúrense que el archivo quede en el _classpath_ al ejecutar la app (por ejemplo, dentro de `resources`).

Si necesitan agregar el campo `descripcion` a `Problema`:

```sql
ALTER TABLE Problema ADD descripcion VARCHAR(255);
```

---

## Compilar y ejecutar
Opciones comunes:

- **IDE (recomendado para este curso):** Abrir el proyecto en NetBeans y ejecutar la clase `FrmPrincipal` (o el `main` que inicie la UI).
---

## Problemas conocidos / Troubleshooting
- **Error por versión de JDK:** Si al compilar les sale un error de versión del JDK, cambien la versión en el `pom.xml`. Por ejemplo, ajustar las propiedades:

```xml
<properties>
  <maven.compiler.source>11</maven.compiler.source>
  <maven.compiler.target>11</maven.compiler.target>
</properties>
```

O usar la versión que tengan instalada (por ejemplo `17`).

- **Errores con el driver JDBC:** Asegúrense de tener la dependencia del driver SQL Server en el `pom.xml`.

---

## Buenas prácticas y entrega
- Crear una rama para su trabajo: `feature/<nombre-equipo>-activistas-problemas`.
- Hacer commits pequeños y con mensajes claros: `feat: CRUD activistas`, `fix: validación telefono`.
- Documentar en el README del equipo los cambios importantes.

### Entregable mínimo (por equipo)
- Módulo Activistas completo (CRUD).
- Módulo Problemas completado (incluye `descripcion`).
- Pantalla funcional para asignar activistas a problemas (guardar en la tabla intermedia).
- Archivo `db.properties` (pueden subir un db.properties.example sin credenciales reales).

---

## Nota final
Este repositorio es **el código base del proyecto**: no está terminado. Les toca **terminar la administración de activistas**, **completar problemas** y **agregar la asignación de activistas a problemas**. Si tienen dudas concretas sobre el modelo de datos, DAO, o la UI, suban un issue o compartan el código y con gusto les oriento.

¡Éxito!

