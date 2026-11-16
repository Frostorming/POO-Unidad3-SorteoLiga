# Sorteo de Liga Profesional - POO Unidad 3

Aplicación desarrollada en **Java** bajo el patrón arquitectónico **Modelo-Vista-Controlador (MVC)** para gestionar el sorteo aleatorio de una liga profesional de 16 equipos (desde octavos de final hasta la gran final), aplicando recursividad, excepciones personalizadas y serialización de objetos.

## 📋 Descripción del Deber
El objetivo de este proyecto es implementar una solución robusta en Java que cumpla con los siguientes requerimientos académicos:
1. **Patrón MVC:** Estructura modular que separa la lógica del negocio, la interfaz de usuario por consola y el controlador mediador.
2. **Algoritmo Recursivo:** Emparejamiento aleatorio de equipos en cada fase de eliminación directa garantizando que ningún equipo repita partido en la misma ronda ni juegue contra sí mismo.
3. **Excepciones Personalizadas:**
   - `NumeroEquiposInvalidoException`: Valida que la cantidad de equipos en cada etapa sea una potencia de 2 (16, 8, 4, 2).
   - `SorteoInvalidoException`: Controla incidencias lógicas durante el proceso recursivo.
4. **Serialización:** Capacidad para guardar y recuperar el estado completo del torneo mediante archivos binarios (`torneo.ser`).

## 📂 Estructura del Proyecto
El código fuente está organizado en los siguientes paquetes lógicos dentro de Eclipse IDE:
```text
src/
├── ups/
│   └── poo/
│       └── torneo/
│           ├── excepciones/    # Clases NumeroEquiposInvalidoException y SorteoInvalidoException
│           ├── modelo/         # Clases Equipo, Partido y Torneo (lógica recursiva y serialización)
│           ├── vista/          # Interfaz de texto en consola (VistaConsola)
│           ├── controlador/    # Mediador entre modelo y vista (TorneoControlador)
│           └── main/           # Clase principal de ejecución (Main)
