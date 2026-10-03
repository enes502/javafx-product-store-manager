# 🛍️ JavaFX Product & Store Management App

A desktop graphical user interface (GUI) application developed in **Java** using **JavaFX** and **Object-Oriented Programming (OOP)** principles. This application manages a retail store inventory with support for books and electronic devices, enforcing strict validation rules and providing dynamic CSS styling.

---

## 🚀 Features

*   **Object-Oriented Hierarchy:**
    *   **Abstract Class (`Product`):** Encapsulates core attributes like name and price, defining a contract for display behaviors.
    *   **Concrete Subclasses (`Book`, `Electronic`):** Implements specific attributes such as page counts, brands, and smart device states.
*   **Interface Implementation (`ProductManager`):** Defines contract methods for adding items and listing store inventory.
*   **Interactive JavaFX GUI:** 
    *   Dynamic input forms built with `GridPane`, `TextField`, and `ComboBox`.
    *   Scrollable layout support via `ScrollPane`.
    *   External CSS styling (`application.css`) for custom button hover effects, backgrounds, and label layouts.
*   **Robust Input Validation:** Validates empty fields, positive numeric constraints for prices and page counts, and integer type checks for numbers.

---

## 📂 Project Structure

```text
├── application/
│   ├── Main.java           # JavaFX application entry point, GUI controllers, and event handlers
│   └── application.css     # Cascading Style Sheet for custom UI component themes
└── README.md               # Project documentation
