# 🏥 Navjivan Hospital Management System

A comprehensive menu-driven Java console application developed for **Java Semester-1 Project**. This system automates daily hospital operations, including patient admissions, doctor assignments, medicine inventory, diagnostic blood reports, billing, and daily financial management with role-based access control.

---

## 📌 Features

### 🔐 Role-Based Access Control
- **Admin Panel**: Complete control over patient records, doctor directory, medicine inventory, blood reports, and financial tracking.
- **Staff Panel**: Simplified interface for patient management and blood report access.
- **Security**: Password-protected login with a maximum of 3 login attempts.

### 👤 Patient Management
- Register new patients (New Case vs. Existing Case).
- Search and update patient details.
- Manage admission and discharge dates.
- Automated bill calculation (including 18% GST).

### 👨‍⚕️ Doctor Directory
- View and search available doctors by specialization.
- Add new doctors to the hospital directory.
- Update doctor information and salary/fee structure.

### 💊 Medicine Inventory
- Track available medicines and stock prices.
- Add new medicines and update pricing.

### 🩸 Blood Report Generation
- Automatic parameter adjustment based on diagnosed condition (e.g., Fever, Diabetes, Heart condition).
- Tracks Hemoglobin, WBC count, Platelet count, Sugar level, and Cholesterol.

### 💰 Financial & Earnings Management
- Track daily hospital revenue.
- Display total hospital earnings and billing summaries.

---

## 🔑 Default Login Credentials

| Role | User ID | Password |
| :--- | :--- | :--- |
| **Admin** | `Navjivanmain` | `NavjMA@123` |
| **Staff** | `Navjivan` | `Navj@123` |

---

## 🛠️ Object-Oriented Concepts Implemented

- **Inheritance**: `Patient` and `Doctor` classes inherit shared attributes from the `Person` base class.
- **Encapsulation**: Private and protected data members with public getter/setter access control.
- **Abstraction & Polymorphism**: Method structure for menu handlers and record displays.
- **Data Consistency**: Single-instance `HospitalSystem` management across login sessions.

---

## 📁 Project Structure

```text
Java Sem-1 Project/
├── src/
│   ├── Main.java           # Entry point and Authentication loop
│   ├── HospitalSystem.java # Core logic & menu handlers (Admin/Staff)
│   ├── Person.java         # Base class for inheritance
│   ├── Patient.java        # Patient model & billing calculations
│   ├── Doctor.java         # Doctor model & specialization mapping
│   ├── Medicine.java       # Medicine model & inventory
│   └── BloodReport.java    # Diagnostic parameters generator
├── .gitignore              # Files ignored by Git
└── README.md               # Project documentation
```

---

## 🚀 How to Run Locally

### Option 1: Using Command Prompt / Terminal

1. **Clone or Download** the project files.
2. Open terminal/command prompt and navigate to the project directory:
   ```bash
   cd "d:/prince patel java/Java Sem-1 Project"
   ```
3. Compile all Java files:
   ```bash
   javac -d bin src/*.java
   ```
4. Run the application:
   ```bash
   java -cp bin Main
   ```

### Option 2: Using IntelliJ IDEA or VS Code

1. Open **IntelliJ IDEA** or **VS Code**.
2. Select **Open Folder** and choose `Java Sem-1 Project`.
3. Open `src/Main.java`.
4. Click **Run** or press `Shift + F10` (IntelliJ) / `F5` (VS Code).

---

## 📝 License & Attribution

Developed as a **Semester-1 Java Project**. Feel free to use, modify, and improve!
