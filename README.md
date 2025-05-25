# InventoryManagementSystem# 📦 Java GUI Inventory Management System

A **Java Swing-based Inventory Management System** built as a college-level project. This application allows you to manage products and sales using a graphical interface, with data stored locally in files — no database required.

---

## 🖥️ Features

- 📋 Add and manage products (ID, name, quantity, price)
- 🛒 Sales panel to sell products and reduce stock
- 💾 Data stored in local `.dat` file using Java serialization
- 🧩 Clean GUI with tabbed interface (Products, Sales)
- 🔄 Auto-load and save data across sessions

---

## 🧱 Requirements

- Java JDK 8 or higher
- An IDE like IntelliJ IDEA, Eclipse, or NetBeans
- No external libraries required

---

## 🧰 Project Structure

src/
├── gui/
│ ├── MainFrame.java // Main application window
│ ├── ProductPanel.java // Panel for adding and viewing products
│ └── SalesPanel.java // Panel for selling products
├── model/
│ └── Product.java // Represents a product object
├── service/
│ └── InventoryManager.java // Handles reading/writing product data to file


---

## 🚀 How to Run

1. Open the project in your Java IDE.
2. Make sure the `src/` folder is marked as your source root.
3. Run `MainFrame.java`.
4. The application will launch with two tabs:
   - **Products**: Add products with ID, name, quantity, and price.
   - **Sales**: Sell a product and automatically reduce its stock.

---

## 💾 Data Storage

- Products are saved in a file called `products.dat`.
- Data is automatically loaded when the app starts and saved when products are added or updated.
- No need to manually manage files.

---

## 📸 Screenshots 

![Screenshot 2025-05-25 192714](https://github.com/user-attachments/assets/f93e293d-624f-47d5-aa57-f65626ebf43a)
