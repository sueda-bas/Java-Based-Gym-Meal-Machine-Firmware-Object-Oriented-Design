# 🍽️ Java-Based-Gym-Meal-Machine-Firmware-Object-Oriented-Design

This project is a **Java-based Gym Meal Machine (GMM) firmware** that follows **Object-Oriented Programming (OOP) principles**, including **abstraction, encapsulation, and clean code design**. The GMM allows gym members to purchase meals based on their **nutritional needs** or by selecting a specific slot.

## 📌 Features
- **Supports 3 Macronutrient-Based Selection Methods**:
  - **Protein-based** meal selection.
  - **Carbohydrate-based** meal selection.
  - **Fat-based** meal selection.
- **Calorie Calculation**: Automatically calculates calorie values for each meal using: calorie(kcal) = 4 * protein(g) + 4 * carbohydrate(g) + 9 * fat(g)
- - **Slot-Based Meal Selection**: Customers can also select meals using **slot numbers**.
- **Limited Slot Capacity**:
- Each slot holds **up to 10 meals**.
- If a slot is full, the next available slot is used.
- **Transaction Handling**:
- Accepts only **1, 5, 10, 20, 50, 100, 200 TL**.
- Returns **change** after purchase.
- **Error Handling & Informational Messages**:
- If an invalid selection is made, an **INFO message** is displayed and money is returned.

## 🎮 How It Works
1. **The GMM is loaded** with meals from `Product.txt`, maintaining slot capacity rules.
2. **Gym members make purchases** using `Purchase.txt`, selecting meals by macronutrient or slot number.
3. **The system processes transactions**, dispensing meals and returning any change.
4. **Final machine state is displayed**, showing remaining meal stock.

## 🚀 Running the Program
Compile and run the program using:
```bash
javac8 Main.java
java8 Main Product.txt Purchase.txt GMMOutput.txt





