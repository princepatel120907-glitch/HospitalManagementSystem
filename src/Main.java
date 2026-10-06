import java.util.*;

// ================= MAIN CLASS =================
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Credentials
        String adminId = "Navjivanmain";
        String adminPassword = "NavjMA@123";
        String staffId = "Navjivan";
        String staffPassword = "Navj@123";

        System.out.println("*-*-🏥Welcome to the Navjivan Hospital Management System-*-*");

        // Create a single HospitalSystem instance to maintain data consistency
        HospitalSystem hospital = null;

        while (true) {
            // Ask user whether to login as admin or staff
            int loginType = 0;
            while (loginType < 1 || loginType > 2) {
                System.out.println("\n--- SELECT LOGIN TYPE ---");
                System.out.println("1. Admin Login");
                System.out.println("2. Staff Login");
                System.out.print("Enter your choice (1-2): ");

                if (sc.hasNextInt()) {
                    loginType = sc.nextInt();
                    if (loginType < 1 || loginType > 2) {
                        System.out.println("Invalid choice! Please enter 1 or 2 ❌");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number ❌");
                    sc.next();
                }
            }

            int attempts = 0;
            boolean loggedIn = false;
            boolean isAdmin = false;

            while (attempts < 3 && !loggedIn) {
                System.out.println("\n--- LOGIN ---");
                System.out.print("Enter ID: ");
                String id = sc.next();
                System.out.print("Enter Password: ");
                String password = sc.next();

                if (loginType == 1) {
                    if (adminId.equals(id) && adminPassword.equals(password)) {
                        loggedIn = true;
                        isAdmin = true;
                        System.out.println("Admin login successful! ✔");
                    } else {
                        attempts++;
                        if (attempts < 3) {
                            System.out.println("Invalid ID/Password. Attempts left: " + (3 - attempts));
                        } else {
                            System.out.println("Too many attempts. Returning to login type selection...");
                        }
                    }
                } else {
                    if (staffId.equals(id) && staffPassword.equals(password)) {
                        loggedIn = true;
                        isAdmin = false;
                        System.out.println("Staff login successful! ✔");
                    } else {
                        attempts++;
                        if (attempts < 3) {
                            System.out.println("Invalid ID/Password. Attempts left: " + (3 - attempts));
                        } else {
                            System.out.println("Too many attempts. Returning to login type selection...");
                        }
                    }
                }
            }

            if (loggedIn) {
                // Reuse the same hospital system instance to maintain data
                if (hospital == null) {
                    hospital = new HospitalSystem(sc, isAdmin);

                } else {
                    // Update the isAdmin flag for the existing instance
                    hospital.updateAdminStatus(isAdmin);
                }

                hospital.start();

                System.out.print("\nDo you want to login again? (y/n): ");
                String again = sc.next();
                if (!again.equalsIgnoreCase("y")) {
                    System.out.println("Thank you for using Navjivan Hospital System!");
                    break;
                }
            }
        }
        sc.close();
    }
}






// ================= MAIN HOSPITAL SYSTEM CLASS =================
