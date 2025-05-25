# 🧩 Proyecto Sudoku – Documentación Técnica Completa

## 📌 1. Análisis del Proyecto

### 🎯 Objetivo
Desarrollar una aplicación de escritorio en Java para jugar al Sudoku. Esta debe permitir generar tableros por dificultad, validar jugadas, resolver automáticamente y proporcionar una interfaz gráfica interactiva.

### ✅ Requisitos Funcionales

| Código | Descripción |
|--------|-------------|
| RF1 | Generar tableros Sudoku según una dificultad seleccionada. |
| RF2 | Permitir al usuario introducir números en celdas no fijas. |
| RF3 | Validar que el número introducido es válido según reglas de Sudoku. |
| RF4 | Resolver automáticamente el tablero si se desea. |
| RF5 | Comprobar si el tablero está correctamente resuelto. |
| RF6 | Mostrar el tablero y permitir interacción mediante interfaz gráfica. |

### ❗ Requisitos No Funcionales

| Código | Descripción |
|--------|-------------|
| RNF1 | La aplicación debe estar implementada en Java. |
| RNF2 | Debe utilizarse Swing para la interfaz gráfica. |
| RNF3 | El código debe estar organizado en paquetes y bien documentado. |

---

## 🧱 2. Arquitectura y Organización del Código

### 🗂️ Estructura de paquetes

```plaintext
sudoku/
│
├── logica/
│   ├── Sudoku.java
│   └── GeneradorSudoku.java
│
├── vista/
│   └── SudokuGUI.java
│
└── principal/
    └── Main.java
