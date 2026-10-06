// ================= CLASS: BLOOD REPORT =================
class BloodReport {
    String disease;
    String[][] parameters;

    BloodReport(String disease) {
        this.disease = disease;
        this.parameters = new String[5][2];
        generateReport();
    }

    void generateReport() {
        parameters[0][0] = "Hemoglobin";
        parameters[0][1] = "14.0";
        parameters[1][0] = "WBC Count";
        parameters[1][1] = "7000.0";
        parameters[2][0] = "Platelet Count";
        parameters[2][1] = "250000.0";
        parameters[3][0] = "Sugar Level";
        parameters[3][1] = "100.0";
        parameters[4][0] = "Cholesterol";
        parameters[4][1] = "200.0";

        if (disease != null) {
            if (disease.equalsIgnoreCase("Fever")) {
                parameters[1][1] = "12000.0";
                parameters[0][1] = "13.5";
            } else if (disease.equalsIgnoreCase("Diabetes")) {
                parameters[3][1] = "180.0";
            } else if (disease.equalsIgnoreCase("Heart")) {
                parameters[4][1] = "250.0";
            }
        }
    }

    void displayReport() {
        System.out.println("\n========================================");
        System.out.println("           BLOOD REPORT PARAMETERS");
        System.out.println("========================================");
        for (int i = 0; i < parameters.length; i++) {
            System.out.println(parameters[i][0] + ": " + parameters[i][1]);
        }
        System.out.println("========================================");
    }
}

