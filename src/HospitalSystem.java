import java.util.Scanner;

class HospitalSystem {
    Scanner sc;
    boolean isAdmin;
    Patient[] patients = new Patient[100];
    Doctor[] doctors = new Doctor[50];
    Medicine[] medicines = new Medicine[50];
    String[][] dailyEarnings = new String[100][2];

    int patientCount = 0;
    int doctorCount = 0;
    int medicineCount = 0;
    int earningCount = 0;

    final double GST_RATE = 0.18;

    HospitalSystem(Scanner sc, boolean isAdmin) {
        this.sc = sc;
        this.isAdmin = isAdmin;
        initializeData();
    }


    void updateAdminStatus(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    void initializeData() {
        // Doctors
        doctors[doctorCount++] = new Doctor(101, "Dr Shah", "Fever", 50000);
        doctors[doctorCount++] = new Doctor(102, "Dr Mehta", "Heart", 60000);
        doctors[doctorCount++] = new Doctor(103, "Dr Patel", "Skin", 55000);
        doctors[doctorCount++] = new Doctor(104, "Dr Joshi", "Diabetes", 65000);
        doctors[doctorCount++] = new Doctor(105, "Dr Rana", "Chest", 70000);
        doctors[doctorCount++] = new Doctor(106, "Dr Parmar", "ENT", 78000);
        doctors[doctorCount++] = new Doctor(107, "Dr Sharma", "EYE", 50000);
        doctors[doctorCount++] = new Doctor(108, "Dr Darji", "Stomach Disease", 45000);
        doctors[doctorCount++] = new Doctor(109, "Dr Agrwal", "Cough", 65000);
        doctors[doctorCount++] = new Doctor(110, "Dr Rana", "Bone", 70000);
        doctors[doctorCount++] = new Doctor(111, "Dr Dangar", "Brain", 90000);

        // Medicines
        medicines[medicineCount++] = new Medicine("Paracetamol", 50);
        medicines[medicineCount++] = new Medicine("Aspirin", 30);
        medicines[medicineCount++] = new Medicine("Ibuprofen", 40);
        medicines[medicineCount++] = new Medicine("Amoxicillin", 60);
        medicines[medicineCount++] = new Medicine("Insulin", 200);

        // Patients
        Patient p1 = new Patient("Rahul", "9876543210", "Fever");

        p1.setPaid(true);
        p1.setBill(1500);
        p1.setAdmissionDate("01012024");

        Patient p2 = new Patient("Amit", "9123456789", "Heart");


        p2.setPaid(true);
        p2.setBill(2000);
        p2.setAdmissionDate("02012024");

        patients[patientCount++] = p1;
        patients[patientCount++] = p2;

        // Earnings
        dailyEarnings[earningCount++] = new String[]{"01012024", "1500.0"};
        dailyEarnings[earningCount++] = new String[]{"02012024", "2000.0"};
    }

    void start() {
        if (isAdmin) {
            showAdminMenu();
        } else {
            showStaffMenu();
        }
    }

    // ================= ADMIN MENU =================
    void showAdminMenu() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("   NAVJIVAN HOSPITAL (ADMIN PANEL)");
            System.out.println("========================================");
            System.out.println("1.  Add Patient");
            System.out.println("2.  Find Patient");
            System.out.println("3.  Update Patient");
            System.out.println("4.  Add Doctor");
            System.out.println("5.  Update Doctor");
            System.out.println("6.  Add Medicine");
            System.out.println("7.  Update Medicine");
            System.out.println("8.  Blood Report");
            System.out.println("9.  Display All Patients");
            System.out.println("10. Display All Doctors");
            System.out.println("11. Display All Medicines");
            System.out.println("12. Money Management");
            System.out.println("13. Logout");
            System.out.print("Enter your choice (1-13): ");

            int choice = getValidInt();
            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    findPatient();
                    break;
                case 3:
                    updatePatient();
                    break;
                case 4:
                    addDoctor();
                    break;
                case 5:
                    updateDoctor();
                    break;
                case 6:
                    addMedicine();
                    break;
                case 7:
                    updateMedicine();
                    break;
                case 8:
                    bloodReport();
                    break;
                case 9:
                    displayAllPatients();
                    break;
                case 10:
                    displayAllDoctors();
                    break;
                case 11:
                    displayAllMedicines();
                    break;
                case 12:
                    moneyManagement();
                    break;
                case 13:
                    System.out.println("\nAdmin logged out successfully!");
                    return;
                default:
                    System.out.println("Invalid Choice! Please enter 1-13 ❌");
            }
        }
    }

    // ================= STAFF MENU =================
    void showStaffMenu() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("   NAVJIVAN HOSPITAL (STAFF PANEL)");
            System.out.println("========================================");
            System.out.println("1. Add Patient");
            System.out.println("2. Find Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Blood Report");
            System.out.println("5. Logout");
            System.out.print("Enter your choice (1-5): ");

            int choice = getValidInt();
            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    findPatient();
                    break;
                case 3:
                    updatePatient();
                    break;
                case 4:
                    bloodReport();
                    break;
                case 5:
                    System.out.println("\nStaff logged out successfully!");
                    return;
                default:
                    System.out.println("Invalid Choice! Please enter 1-5 ❌");
            }
        }
    }

    // ================= ADD PATIENT =================
    void addPatient() {
        System.out.println("\n========================================");
        System.out.println("          ADD NEW PATIENT");
        System.out.println("========================================");

        int caseType = 0;
        while (caseType < 1 || caseType > 2) {
            System.out.println("1. New Case");
            System.out.println("2. Old Case");
            System.out.print("Choose: ");

            if (sc.hasNextInt()) {
                caseType = sc.nextInt();
                if (caseType < 1 || caseType > 2) {
                    System.out.println("Invalid choice! Enter 1 or 2 ❌");
                }
            } else {
                System.out.println("Invalid input! Please enter a number ❌");
                sc.next();
            }
        }

        Patient patient;
        double baseAmount = (caseType == 1) ? 1500 : 750;

        if (caseType == 1) {
            patient = new Patient("", "", "");
            patient.setPatientDetails(sc);
            assignDoctor(patient);
        } else {
            Patient oldPatient = findPatientByMobile();
            if (oldPatient == null) return;
            patient = oldPatient;
        }

        double totalBill = calculateBill(baseAmount);
        System.out.println("\nBase Amount: Rs." + baseAmount);
        System.out.println("GST (18%): Rs." + (baseAmount * GST_RATE));
        System.out.println("Total to Pay: Rs." + totalBill);

        String date = patient.getAdmissionDate();
        if (processPayment(totalBill, date)) {
            patient.setBill(totalBill);
            patient.setPaid(true);

            System.out.print("\nDo you want to purchase medicines? (y/n): ");
            if (sc.next().equalsIgnoreCase("y")) {
                double medTotal = assignMedicines(patient.getDisease());
                double medBill = calculateBill(medTotal);
                System.out.println("Medicine Bill: Rs." + medBill);
                if (processPayment(medBill, date)) {
                    patient.setBill(patient.getBill() + medBill);
                }
            }

            if (patientCount < patients.length) {
                patients[patientCount] = patient;
                patientCount++;

                displayPatientBill(patient);
                System.out.println("\n✅ Patient added successfully!");
            } else {
                System.out.println("\n❌ Hospital at full capacity! Cannot add more patients.");
            }
        } else {
            System.out.println("\n❌ Payment failed. Patient not added.");
        }
    }

    // ================= FIND PATIENT =================
    void findPatient() {
        System.out.print("Enter Patient Mobile Number: ");
        String mobile = sc.next();

        for (int i = 0; i < patientCount; i++) {
            if (patients[i].getContact().equals(mobile)) {
                displayPatientDetails(patients[i]);
                return;
            }
        }
        System.out.println("Patient not found!");
    }

    // ================= UPDATE PATIENT =================
    void updatePatient() {
        System.out.print("\nEnter Patient Mobile Number to update: ");
        String mobile = sc.next();

        Patient patient = null;
        int patientIndex = -1;

        for (int i = 0; i < patientCount; i++) {
            if (patients[i].getContact().equals(mobile)) {
                patient = patients[i];
                patientIndex = i;
                break;
            }
        }

        if (patient == null) {
            System.out.println("\n❌ Patient not found!");
            return;
        }

        System.out.println("\nCurrent Patient Details:");
        displayPatientDetails(patient);

        int choice = 0;
        while (choice < 1 || choice > 6) {
            System.out.println("\nWhat to update?");
            System.out.println("1. Name");
            System.out.println("2. Age");
            System.out.println("3. Disease");
            System.out.println("4. Payment Status");
            System.out.println("5. Admission Date");
            System.out.println("6. Cancel");
            System.out.print("Choose: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        String newName;

                        while (true) {
                            System.out.print("Enter new name: ");
                            newName = sc.next();

                            boolean valid = true;

                            for (int i = 0; i < newName.length(); i++) {
                                char ch = newName.charAt(i);

                                if (!((ch >= 'A' && ch <= 'Z') ||
                                        (ch >= 'a' && ch <= 'z') )) {
                                    valid = false;
                                    break;
                                }
                            }

                            if (valid) {
                                patient.name = newName;
                                break;
                            } else {
                                System.out.println("❌ Invalid name! Only alphabets  allowed.");
                            }
                        }

                        System.out.println("✅ Name updated!");
                        break;
                    case 2:
                        System.out.print("Enter new age: ");
                        patient.setAge(getValidInt());
                        System.out.println("✅ Age updated!");
                        break;
                    case 3:
                        System.out.print("Enter new disease: ");
                        patient.setDisease(sc.next());
                        assignDoctor(patient);
                        System.out.println("✅ Disease updated!");
                        break;
                    case 4:
                        int payChoice = 0;
                        while (payChoice < 1 || payChoice > 2) {
                            System.out.println("1. Mark as Paid");
                            System.out.println("2. Mark as Unpaid");
                            System.out.print("Choose: ");

                            if (sc.hasNextInt()) {
                                payChoice = sc.nextInt();
                                if (payChoice == 1) {
                                    patient.setPaid(true);
                                    System.out.println("✅ Payment status updated to Paid!");
                                } else if (payChoice == 2) {
                                    patient.setPaid(false);
                                    System.out.println("✅ Payment status updated to Unpaid!");
                                } else {
                                    System.out.println("Invalid choice! Enter 1 or 2 ❌");
                                }
                            } else {
                                System.out.println("Invalid input! Please enter a number ❌");
                                sc.next();
                            }
                        }
                        break;
                    case 5:
                        String date;

                        while (true) {
                            System.out.print("Enter Date (DD/MM/YYYY): ");
                            date = sc.next();

                            // simple format check 10 chars → 12/05/2025
                            if (date.length() == 10 &&
                                    date.charAt(2) == '/' &&
                                    date.charAt(5) == '/') {

                                patient.date = date;   // store date
                                break;
                            } else {
                                System.out.println("❌ Invalid date format! Use DD/MM/YYYY");
                            }
                        }

                        patient.setAdmissionDate(date);
                        System.out.println("✅ Admission date updated!");
                        break;
                    case 6:
                        System.out.println("Update cancelled.");
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter 1-6 ❌");
                        choice = 0;
                }
            } else {
                System.out.println("Invalid input! Please enter a number ❌");
                sc.next();
            }
        }

        // Update the patient in the array
        if (patientIndex != -1) {
            patients[patientIndex] = patient;
        }
    }

    // ================= ADD DOCTOR =================
    void addDoctor() {
        System.out.println("\n========================================");
        System.out.println("          ADD NEW DOCTOR");
        System.out.println("========================================");

        System.out.print("Enter Doctor ID: ");
        int id = getValidInt();

        boolean exists = false;

        for (int i = 0; i < doctorCount; i++) {
            if (doctors[i].getId().equals("D" + id)) {
                exists = true;
                break;
            }
        }

        if (exists) {
            System.out.println("\n❌ Doctor with this ID already exists!");
            return;
        }

        String name;

        while (true) {
            System.out.print("Enter Doctor Name: ");
            name = sc.next();

            boolean valid = true;

            for (int i = 0; i < name.length(); i++) {
                char ch = name.charAt(i);

                if (!((ch >= 'A' && ch <= 'Z') ||
                        (ch >= 'a' && ch <= 'z') ||
                        ch == '.')) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                break;
            } else {
                System.out.println("❌ Invalid name! Only alphabets and '.' allowed.");
            }
        }

        System.out.print("Enter Specialization: ");
        String spec = sc.next();
        System.out.print("Enter Monthly Salary: ");
        double salary = getValidDouble();

        if (doctorCount < doctors.length) {
            doctors[doctorCount] = new Doctor(id, name, spec, salary);
            doctorCount++;
            System.out.println("\n✅ Doctor added successfully!");
        } else {
            System.out.println("\n❌ Cannot add more doctors! Hospital at full capacity.");
        }
    }

    // ================= UPDATE DOCTOR =================
    void updateDoctor() {
        System.out.print("\nEnter Doctor ID to update: ");
        int id = getValidInt();

        Doctor doctor = null;
        int doctorIndex = -1;

        for (int i = 0; i < doctorCount; i++) {
            if (doctors[i].getId().equals("D" + id)) {
                doctor = doctors[i];
                doctorIndex = i;
                break;
            }
        }

        if (doctor == null) {
            System.out.println("\n❌ Doctor not found!");
            return;
        }

        System.out.println("\nCurrent Doctor Details:");
        displayDoctorDetails(doctor);

        int choice = 0;
        while (choice < 1 || choice > 4) {
            System.out.println("\nWhat to update?");
            System.out.println("1. Name");
            System.out.println("2. Specialization");
            System.out.println("3. Salary");
            System.out.println("4. Cancel");
            System.out.print("Choose: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        String newName;

                        while (true) {
                            System.out.print("Enter new name: ");
                            newName = sc.next();

                            boolean valid = true;

                            for (int i = 0; i < newName.length(); i++) {
                                char ch = newName.charAt(i);

                                if (!((ch >= 'A' && ch <= 'Z') ||
                                        (ch >= 'a' && ch <= 'z') ||
                                        ch == '.')) {
                                    valid = false;
                                    break;
                                }
                            }

                            if (valid) {
                                doctor.name = newName;
                                break;
                            } else {
                                System.out.println("❌ Invalid name! Only alphabets and '.' allowed.");
                            }
                        }

                        System.out.println("✅ Name updated!");
                        break;
                    case 2:
                        System.out.print("Enter new specialization: ");
                        doctor.setSpecialization(sc.next());
                        System.out.println("✅ Specialization updated!");
                        break;
                    case 3:
                        System.out.print("Enter new salary: ");
                        doctor.setMonthlySalary(getValidDouble());
                        System.out.println("✅ Salary updated!");
                        break;
                    case 4:
                        System.out.println("Update cancelled.");
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter 1-4 ❌");
                        choice = 0;
                }
            } else {
                System.out.println("Invalid input! Please enter a number ❌");
                sc.next();
            }
        }

        // Update the doctor in the array
        if (doctorIndex != -1) {
            doctors[doctorIndex] = doctor;
        }
    }

    // ================= ADD MEDICINE =================
    void addMedicine() {
        System.out.println("\n========================================");
        System.out.println("          ADD NEW MEDICINE");
        System.out.println("========================================");
        System.out.print("Enter Medicine Name: ");
        String name = sc.next();

        boolean exists = false;
        for (int i = 0; i < medicineCount; i++) {
            if (medicines[i].getName().equalsIgnoreCase(name)) {
                exists = true;
                break;
            }
        }

        if (exists) {
            System.out.println("\n❌ Medicine already exists!");
            return;
        }

        System.out.print("Enter Price: ");
        double price = getValidDouble();

        if (medicineCount < medicines.length) {
            medicines[medicineCount] = new Medicine(name, price);
            medicineCount++;
            System.out.println("\n✅ Medicine added successfully!");
        } else {
            System.out.println("\n❌ Cannot add more medicines! Storage full.");
        }
    }

    // ================= UPDATE MEDICINE =================
    void updateMedicine() {
        if (medicineCount == 0) {
            System.out.println("No medicines available!");
            return;
        }

        System.out.print("Enter medicine number (1-" + medicineCount + "): ");
        int index = getValidInt() - 1;

        if (index < 0 || index >= medicineCount) {
            System.out.println("Invalid selection!");
            return;
        }

        Medicine medicine = medicines[index];

        int choice = 0;
        while (choice < 1 || choice > 3) {
            System.out.println("\nUpdate:");
            System.out.println("1. Name");
            System.out.println("2. Price");
            System.out.println("3. Cancel");
            System.out.print("Choose: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter new name: ");
                        medicine.setName(sc.next());
                        System.out.println("✅ Name updated!");
                        break;
                    case 2:
                        System.out.print("Enter new price: ");
                        medicine.setPrice(getValidDouble());
                        System.out.println("✅ Price updated!");
                        break;
                    case 3:
                        System.out.println("Update cancelled.");
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter 1-3 ❌");
                        choice = 0;
                }
            } else {
                System.out.println("Invalid input! Please enter a number ❌");
                sc.next();
            }
        }

        // Update the medicine in the array
        medicines[index] = medicine;
    }

    // ================= BLOOD REPORT =================
    void bloodReport() {
        System.out.print("Enter Patient Mobile Number: ");
        String mobile = sc.next();

        Patient patient = null;

        for (int i = 0; i < patientCount; i++) {
            if (patients[i].getContact().equals(mobile)) {
                patient = patients[i];
                break;
            }
        }

        if (patient == null) {
            System.out.println("\n❌ Patient not found!");
            return;
        }

        double reportFee = 2500;
        double total = calculateBill(reportFee);

        System.out.println("\nBlood Report Charges:");
        System.out.println("Report Fee: Rs." + reportFee);
        System.out.println("GST (18%): Rs." + (reportFee * GST_RATE));
        System.out.println("Total: Rs." + total);

        System.out.print("Enter date for payment (DDMMYYYY): ");
        String date = sc.next();

        if (processPayment(total, date)) {
            BloodReport report = new BloodReport(patient.getDisease());
            System.out.println("\n========================================");
            System.out.println("           BLOOD REPORT");
            System.out.println("========================================");
            System.out.println("Patient: " + patient.getName());
            System.out.println("Disease: " + patient.getDisease());
            System.out.println("----------------------------------------");
            report.displayReport();

            patient.setBill(patient.getBill() + total);
            System.out.println("\n✅ Report generated successfully!");
        }
    }

    // ================= DISPLAY ALL PATIENTS =================
    void displayAllPatients() {
        if (patientCount == 0) {
            System.out.println("\n❌ No patients found!");
            return;
        }

        System.out.println("\n=============================================================");
        System.out.println("                     ALL PATIENTS");
        System.out.println("=============================================================");
        System.out.println("S.No  Name  Mobile  Age  Disease  Doctor  Bill  Status");
        System.out.println("-------------------------------------------------------------");

        for (int i = 0; i < patientCount; i++) {
            Patient p = patients[i];
            String doctorName = (p.getAssignedDoctor() != null) ? p.getAssignedDoctor().getName() : "Not Assigned";
            String status = p.isPaid() ? "Paid" : "Unpaid";

            System.out.println((i + 1) + ". " + p.getName() + " " + p.getContact() + " " + p.getAge() +
                    " " + p.getDisease() + " " + doctorName + " Rs." + p.getBill() + " " + status);
        }
        System.out.println("=============================================================");
        System.out.println("Total Patients: " + patientCount);
    }

    // ================= DISPLAY ALL DOCTORS =================
    void displayAllDoctors() {
        if (doctorCount == 0) {
            System.out.println("\n❌ No doctors found!");
            return;
        }

        System.out.println("\n========================================================");
        System.out.println("                     ALL DOCTORS");
        System.out.println("========================================================");
        System.out.println("S.No  ID  Name  Specialization  Monthly Salary");
        System.out.println("--------------------------------------------------------");

        for (int i = 0; i < doctorCount; i++) {
            Doctor d = doctors[i];
            System.out.println((i + 1) + ". " + d.getId() + " " + d.getName() + " " +
                    d.getSpecialization() + " Rs." + d.getMonthlySalary());
        }
        System.out.println("========================================================");
        System.out.println("Total Doctors: " + doctorCount);
    }

    // ================= DISPLAY ALL MEDICINES =================
    void displayAllMedicines() {
        if (medicineCount == 0) {
            System.out.println("\n❌ No medicines found!");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("              ALL MEDICINES");
        System.out.println("========================================");
        System.out.println("S.No  Medicine Name  Price");
        System.out.println("----------------------------------------");

        for (int i = 0; i < medicineCount; i++) {
            Medicine m = medicines[i];
            System.out.println((i + 1) + ". " + m.getName() + " Rs." + m.getPrice());
        }
        System.out.println("========================================");
        System.out.println("Total Medicines: " + medicineCount);
    }

    // ================= MONEY MANAGEMENT =================
    void moneyManagement() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("        MONEY MANAGEMENT");
            System.out.println("========================================");
            System.out.println("1. Daily Earnings");
            System.out.println("2. Monthly Earnings");
            System.out.println("3. Yearly Earnings");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose: ");

            int choice = getValidInt();
            switch (choice) {
                case 1:
                    showDailyEarnings();
                    break;
                case 2:
                    showMonthlyEarnings();
                    break;
                case 3:
                    showYearlyEarnings();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice! Please enter 1-4 ❌");
            }
        }
    }

    void showDailyEarnings() {
        if (earningCount == 0) {
            System.out.println("\nNo earnings recorded yet!");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("      DAILY EARNINGS");
        System.out.println("========================================");
        System.out.println("Date  Earnings");
        System.out.println("----------------------------------------");

        double total = 0;
        for (int i = 0; i < earningCount; i++) {
            String[] earning = dailyEarnings[i];
            String date = earning[0];
            double amount = Double.parseDouble(earning[1]);

            if (date.length() == 8) {
                String formattedDate = date.substring(0, 2) + "/" + date.substring(2, 4) + "/" + date.substring(4);
                System.out.println(formattedDate + " Rs." + amount);
                total += amount;
            }
        }
        System.out.println("----------------------------------------");
        System.out.println("TOTAL: Rs." + total);
    }

    void showMonthlyEarnings() {
        if (earningCount == 0) {
            System.out.println("\nNo earnings recorded yet!");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("     MONTHLY EARNINGS");
        System.out.println("========================================");
        System.out.println("Month-Year  Earnings");
        System.out.println("----------------------------------------");

        // Create a temporary array to store monthly earnings
        String[][] monthlyEarnings = new String[100][2];
        int monthlyCount = 0;

        for (int i = 0; i < earningCount; i++) {
            String[] earning = dailyEarnings[i];
            String date = earning[0];
            if (date.length() == 8) {
                String monthYear = date.substring(2, 4) + date.substring(4);
                double amount = Double.parseDouble(earning[1]);

                boolean found = false;
                for (int j = 0; j < monthlyCount; j++) {
                    if (monthlyEarnings[j][0].equals(monthYear)) {
                        double oldAmount = Double.parseDouble(monthlyEarnings[j][1]);
                        monthlyEarnings[j][1] = String.valueOf(oldAmount + amount);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    monthlyEarnings[monthlyCount][0] = monthYear;
                    monthlyEarnings[monthlyCount][1] = String.valueOf(amount);
                    monthlyCount++;
                }
            }
        }

        String[] months = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (int i = 0; i < monthlyCount; i++) {
            String[] monthly = monthlyEarnings[i];
            String my = monthly[0];
            if (my.length() == 6) {
                int month = Integer.parseInt(my.substring(0, 2));
                String year = my.substring(2);
                System.out.println(months[month] + "-" + year + " Rs." + monthly[1]);
            }
        }
    }

    void showYearlyEarnings() {
        if (earningCount == 0) {
            System.out.println("\nNo earnings recorded yet!");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("     YEARLY EARNINGS");
        System.out.println("========================================");
        System.out.println("Year  Earnings");
        System.out.println("----------------------------------------");

        // Create a temporary array to store yearly earnings
        String[][] yearlyEarnings = new String[100][2];
        int yearlyCount = 0;

        for (int i = 0; i < earningCount; i++) {
            String[] earning = dailyEarnings[i];
            String date = earning[0];
            if (date.length() == 8) {
                String year = date.substring(4);
                double amount = Double.parseDouble(earning[1]);

                boolean found = false;
                for (int j = 0; j < yearlyCount; j++) {
                    if (yearlyEarnings[j][0].equals(year)) {
                        double oldAmount = Double.parseDouble(yearlyEarnings[j][1]);
                        yearlyEarnings[j][1] = String.valueOf(oldAmount + amount);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    yearlyEarnings[yearlyCount][0] = year;
                    yearlyEarnings[yearlyCount][1] = String.valueOf(amount);
                    yearlyCount++;
                }
            }
        }

        for (int i = 0; i < yearlyCount; i++) {
            String[] yearly = yearlyEarnings[i];
            System.out.println(yearly[0] + " Rs." + yearly[1]);
        }
    }

    // ================= HELPER METHODS =================
    Patient findPatientByMobile() {
        System.out.print("Enter Mobile Number: ");
        String mobile = sc.next();

        for (int i = 0; i < patientCount; i++) {
            if (patients[i].getContact().equals(mobile)) {
                return patients[i];
            }
        }

        System.out.println("Patient not found!");
        return null;
    }

    void assignDoctor(Patient patient) {
        // First, find available doctors for this disease
        Doctor[] availableDoctors = new Doctor[doctorCount];
        int availableCount = 0;

        for (int i = 0; i < doctorCount; i++) {
            if (doctors[i].getSpecialization().equalsIgnoreCase(patient.getDisease())) {
                availableDoctors[availableCount] = doctors[i];
                availableCount++;
            }
        }

        if (availableCount == 0) {
            System.out.println("❌ No doctor available for " + patient.getDisease());
            return;
        }

        System.out.println("\nAvailable Doctors for " + patient.getDisease() + ":");
        for (int i = 0; i < availableCount; i++) {
            System.out.println((i + 1) + ". " + availableDoctors[i].getName());
        }

        System.out.print("Choose doctor (1-" + availableCount + "): ");
        int choice = getValidInt(1, availableCount) - 1;
        patient.setAssignedDoctor(availableDoctors[choice]);
        System.out.println("✅ Doctor " + availableDoctors[choice].getName() + " assigned!");
    }

    double assignMedicines(String disease) {
        double total = 0;
        System.out.println("\nPrescribed Medicines:");

        if (disease.equalsIgnoreCase("fever")) {
            System.out.println("1. Paracetamol - Rs.50");
            System.out.println("2. Aspirin - Rs.30");
            total = 80;
        } else if (disease.equalsIgnoreCase("heart")) {
            System.out.println("1. Aspirin - Rs.30");
            total = 30;
        } else if (disease.equalsIgnoreCase("diabetes")) {
            System.out.println("1. Insulin - Rs.200");
            System.out.println("2. Metformin - Rs.80");
            total = 280;
        } else {
            System.out.println("1. Paracetamol - Rs.50");
            total = 50;
        }
        return total;
    }

    double calculateBill(double amount) {
        return amount + (amount * GST_RATE);
    }

    boolean processPayment(double amount, String date) {
        int choice = 0;
        while (choice < 1 || choice > 2) {
            System.out.println("\nPayment Options:");
            System.out.println("1. Cash");
            System.out.println("2. UPI");
            System.out.print("Choose: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice == 1) {
                    return processCashPayment(amount, date);
                } else if (choice == 2) {
                    String[] validHandles = {
                            "@axl", "@ibl", "@sbi", "@paytm",
                            "@okaxis", "@okicici", "@oksbi",
                            "@okhdfcbank", "@ybl"
                    };

                    String upiId;
                    boolean valid;

                    while (true) {
                        System.out.print("Enter UPI ID: ");
                        upiId = sc.next();

                        valid = false;

                        if (upiId.contains("@")) {
                            for (String handle : validHandles) {
                                if (upiId.endsWith(handle)) {
                                    valid = true;
                                    break;
                                }
                            }
                        }

                        if (valid) {
                            System.out.println("✅ UPI Payment Successful!");
                            break;
                        } else {
                            System.out.println("❌ Invalid UPI ID!");
                        }
                    }
                    recordEarning(amount, date);
                    return true;
                } else {
                    System.out.println("Invalid choice! Enter 1 or 2 ❌");
                }
            } else {
                System.out.println("Invalid input! Please enter a number ❌");
                sc.next();
            }
        }
        return false;
    }

    boolean processCashPayment(double amount, String date) {
        System.out.println("Enter cash denominations:");
        int[] denominations = {500, 200, 100, 50, 20, 10, 5, 2, 1};
        double received = 0;

        for (int denom : denominations) {
            System.out.print("Rs." + denom + " notes: ");
            int count = getValidInt();
            received += denom * count;
            if (received >= amount) break;
        }

        if (received >= amount) {
            if (received > amount) {
                System.out.println("Refund: Rs." + (received - amount));
            }
            System.out.println("✅ Payment successful!");
            recordEarning(amount, date);
            return true;
        } else {
            System.out.println("❌ Insufficient cash! Received: Rs." + received + ", Required: Rs." + amount);
            return false;
        }
    }

    void recordEarning(double amount, String date) {
        if (earningCount < dailyEarnings.length) {
            dailyEarnings[earningCount] = new String[]{date, String.valueOf(amount)};
            earningCount++;
        }
    }

    void displayPatientDetails(Patient patient) {
        System.out.println("\n========================================");
        System.out.println("        PATIENT DETAILS");
        System.out.println("========================================");
        System.out.println("Name: " + patient.name);
        System.out.println("Mobile: " + patient.getContact());
        System.out.println("Age: " + patient.getAge());
        System.out.println("Gender: " + patient.getGender());
        System.out.println("Disease: " + patient.getDisease());
        System.out.println("Admission Date: " + patient.getAdmissionDate());
        System.out.println("Doctor: " + ((patient.getAssignedDoctor() != null) ? patient.getAssignedDoctor().getName() : "Not Assigned"));
        System.out.println("Total Bill: Rs." + patient.getBill());
        System.out.println("Payment Status: " + (patient.isPaid() ? "Paid" : "Unpaid"));
        System.out.println("========================================");
    }

    void displayDoctorDetails(Doctor doctor) {
        System.out.println("\n========================================");
        System.out.println("        DOCTOR DETAILS");
        System.out.println("========================================");
        System.out.println("ID: " + doctor.getId());
        System.out.println("Name: " + doctor.getName());
        System.out.println("Specialization: " + doctor.getSpecialization());
        System.out.println("Monthly Salary: Rs." + doctor.getMonthlySalary());
        System.out.println("========================================");
    }

    void displayPatientBill(Patient patient) {
        System.out.println("\n========================================");
        System.out.println("           FINAL BILL");
        System.out.println("========================================");
        System.out.println("Patient Name: " + patient.getName());
        System.out.println("Mobile: " + patient.getContact());
        System.out.println("Disease: " + patient.getDisease());
        System.out.println("Doctor: " + ((patient.getAssignedDoctor() != null) ? patient.getAssignedDoctor().getName() : "Not Assigned"));
        System.out.println("Total Amount: Rs." + patient.getBill());
        System.out.println("Payment Status: " + (patient.isPaid() ? "Paid" : "Unpaid"));
        System.out.println("========================================");
    }

    // ================= INPUT VALIDATION METHODS =================
    int getValidInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input! Enter a number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    int getValidInt(int min, int max) {
        int value;
        while (true) {
            value = getValidInt();
            if (value >= min && value <= max) break;
            System.out.print("Enter between " + min + " and " + max + ": ");
        }
        return value;
    }

    double getValidDouble() {
        while (!sc.hasNextDouble()) {
            System.out.print("Invalid input! Enter a number: ");
            sc.next();
        }
        return sc.nextDouble();
    }
}
