import java.util.Calendar;
import java.util.Scanner;

// ================= CHILD CLASS: PATIENT (Inheritance from Person) =================
class Patient extends Person {
    public String date;
    String gender;
    int age;
    String disease;
    String admissionDate;
    double weight;
    double height;
    double bill;
    boolean paid;
    Doctor assignedDoctor;

    Patient(String name, String contact, String disease) {
        super(name, "P" + (int)(Math.random() * 10000), contact);
        this.disease = disease;
        this.bill = 0;
        this.paid = false;
    }


    void setPatientDetails(Scanner sc) {
        String name;

        while (true) {
            System.out.print("Enter Patient Name: ");
            name = sc.next();

            boolean valid = true;

            for (int i = 0; i < name.length(); i++) {
                char ch = name.charAt(i);

                if (!((ch >= 'A' && ch <= 'Z') ||
                        (ch >= 'a' && ch <= 'z') )) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                this.name = name;

                break;
            } else {
                System.out.println("❌ Invalid name! Only alphabets  allowed.");
            }
        }


        // Gender input with validation
        int gchoice = 0;
        while (gchoice < 1 || gchoice > 2) {
            System.out.println("Enter Patient Gender:");
            System.out.println("1. Male");
            System.out.println("2. Female");
            System.out.print("Choose: ");

            if (sc.hasNextInt()) {
                gchoice = sc.nextInt();
                switch (gchoice) {
                    case 1:
                        this.gender = "Male";
                        break;
                    case 2:
                        this.gender = "Female";
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter 1 for Male or 2 for Female ❌");
                }
            } else {
                System.out.println("Invalid input! Please enter a number ❌");
                sc.next();
            }
        }

        // Age input with validation
        while (true) {
            System.out.print("Enter Patient Age: ");
            if (sc.hasNextInt()) {
                this.age = sc.nextInt();
                if (age > 0 && age <= 100) {
                    break;
                }
                System.out.println("Invalid age! Please enter age between 1 and 100 ❌");
            } else {
                System.out.println("Invalid input! Please enter a number ❌");
                sc.next();
            }
        }

        // Mobile number validation
        sc.nextLine();
        while (true) {
            System.out.print("Enter Mobile Number: ");
            this.contact = sc.nextLine();
            if (this.contact.length() == 10 && (this.contact.charAt(0) == '6' || this.contact.charAt(0) == '7' ||
                    this.contact.charAt(0) == '8' || this.contact.charAt(0) == '9')) {
                break;
            }
            System.out.println("Invalid Mobile Number! Must be 10 digits starting with 6,7,8 or 9 ❌");
        }

        double height;
        while (true) {
            System.out.print("Enter Height : ");
            height = sc.nextDouble();
            if (height > 0 && height <= 200) break;
            else System.out.println("Invalid height!(max 200 cm)");
        }

        // 👉 weight validation here
        double weight;
        while (true) {
            System.out.print("Enter Weight: ");
            weight = sc.nextDouble();
            if (weight > 0 && weight <= 260) break;
            else System.out.println("Invalid weight!(max 260 kg)");
        }
        // Date input with validation
        this.admissionDate = getValidDate(sc);

        // Disease selection
        int choice = 0;
        while (choice < 1 || choice > 11) {
            System.out.println("\nChoose Patient Disease:");
            System.out.println("1. Fever\t2. Cough\t3. Stomach Disease");
            System.out.println("4. Heart\t5. Skin\t\t6. Diabetes");
            System.out.println("7. Bone\t\t8. Eye\t\t9. ENT");
            System.out.println("10. Brain\t11. Chest");
            System.out.print("Enter choice (1-11): ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> this.disease = "Fever";
                    case 2 -> this.disease = "Cough";
                    case 3 -> this.disease = "Stomach Disease";
                    case 4 -> this.disease = "Heart";
                    case 5 -> this.disease = "Skin";
                    case 6 -> this.disease = "Diabetes";
                    case 7 -> this.disease = "Bone";
                    case 8 -> this.disease = "Eye";
                    case 9 -> this.disease = "ENT";
                    case 10 -> this.disease = "Brain";
                    case 11 -> this.disease = "Chest";
                    default -> {
                        System.out.println("Invalid choice! Please enter 1-11 ❌");
                        choice = 0;
                    }
                }
            } else {
                System.out.println("Invalid input! Please enter a number ❌");
                sc.next();
            }
        }

        this.id = "P" + System.currentTimeMillis() % 10000;
    }

    // Helper method for date validation
    String getValidDate(Scanner sc) {
        while (true) {
            System.out.print("Enter admission Date (DDMMYYYY): ");
            String dateStr = sc.next();

            if (dateStr.length() != 8) {
                System.out.println("Date must be exactly 8 digits (DDMMYYYY) ❌");
                continue;
            }

            try {
                int day = Integer.parseInt(dateStr.substring(0, 2));
                int month = Integer.parseInt(dateStr.substring(2, 4));
                int year = Integer.parseInt(dateStr.substring(4, 8));

                // Get current date
                Calendar cal = Calendar.getInstance();
                int currentYear = cal.get(Calendar.YEAR);
                int currentMonth = cal.get(Calendar.MONTH) + 1;
                int currentDay = cal.get(Calendar.DAY_OF_MONTH);

                // Validate year (2000 to current year + 1)
                // NEW (fixed)
                if (year < 2000 || year > 2026) {
                    System.out.println("Year must be between 2000 and 2026 ❌");
                    continue;
                }


                // Check if date is before 12/02/2026
                // Validate year (2000–2026 only)
                if (year < 2000 || year > 2026) {
                    System.out.println("Year must be between 2000 and 2026 ❌");
                    continue;
                }

                // Validate month
                if (month < 1 || month > 12) {
                    System.out.println("Month must be between 01 and 12 ❌");
                    continue;
                }
                // Validate day based on month and year
                int daysInMonth;
                if (month == 2) {
                    // Check for leap year
                    boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                    daysInMonth = isLeapYear ? 29 : 28;
                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    daysInMonth = 30;
                } else {
                    daysInMonth = 31;
                }

                if (day < 1 || day > daysInMonth) {
                    System.out.println("Day must be between 01 and " + daysInMonth + " for month " + month + " ❌");
                    continue;
                }

                // Format date to ensure 2-digit day and month
                return String.format("%02d%02d%04d", day, month, year);

            } catch (NumberFormatException e) {
                System.out.println("Invalid date format! Please enter numbers only ❌");
            } catch (Exception e) {
                System.out.println("Invalid date! Please check the values ❌");
            }
        }
    }

    // Getters and Setters
    String getGender() { return gender; }
    int getAge() { return age; }
    String getDisease() { return disease; }
    String getAdmissionDate() { return admissionDate; }
    double getBill() { return bill; }
    boolean isPaid() { return paid; }
    Doctor getAssignedDoctor() { return assignedDoctor; }

    void setBill(double bill) { this.bill = bill; }
    void setPaid(boolean paid) { this.paid = paid; }
    void setAssignedDoctor(Doctor doctor) { this.assignedDoctor = doctor; }
    void setDisease(String disease) { this.disease = disease; }
    void setAge(int age) { this.age = age; }
    void setGender(String gender) { this.gender = gender; }
    void setAdmissionDate(String date) { this.admissionDate = date; }
}


