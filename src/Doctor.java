// ================= CHILD CLASS: DOCTOR (Inheritance from Person) =================
class Doctor extends Person {
    String specialization;
    double monthlySalary;

    Doctor(int id, String name, String specialization, double monthlySalary) {
        super(name, "D" + id, String.valueOf(id));
        this.specialization = specialization;
        this.monthlySalary = monthlySalary;
    }

    // Getters
    String getSpecialization() { return specialization; }
    double getMonthlySalary() { return monthlySalary; }

    // Setters
    void setSpecialization(String specialization) { this.specialization = specialization; }
    void setMonthlySalary(double monthlySalary) { this.monthlySalary = monthlySalary; }
}

