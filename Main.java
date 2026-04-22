import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClinicSystem system = new ClinicSystem();

        String[] menu = {
                "1. Add Patient",
                "2. Add Doctor",
                "3. Add Appointment",
                "4. List Patients",
                "5. List Doctors",
                "6. List Appointments",
                "7. Exit"
        };

        while (true) {
            System.out.println("\n=== Clinic Appointment System ===");
            for (String option : menu) {
                System.out.println(option);
            }

            System.out.print("Choose an option: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Patient ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Patient Name: ");
                        String name = scanner.nextLine();
                        system.addPatient(new Patient(id, name));
                        System.out.println("Patient added.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter Doctor ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Doctor Name: ");
                        String name = scanner.nextLine();
                        system.addDoctor(new Doctor(id, name));
                        System.out.println("Doctor added.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter Patient ID: ");
                        int patientId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Doctor ID: ");
                        int doctorId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Date: ");
                        String date = scanner.nextLine();

                        Patient patient = system.searchPatientById(patientId);
                        Doctor doctor = system.searchDoctorById(doctorId);

                        if (patient == null || doctor == null) {
                            System.out.println("Invalid patient or doctor ID.");
                            break;
                        }

                        system.addAppointment(new Appointment(patient, doctor, date));
                        System.out.println("Appointment added.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;

                case 4:
                    system.listPatients();
                    break;

                case 5:
                    system.listDoctors();
                    break;

                case 6:
                    system.listAppointments();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
