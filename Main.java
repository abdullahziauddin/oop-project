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
                "7. Delete Patient",
                "8. Delete Doctor",
                "9. Update Patient",
                "10. Update Doctor",
                "11. Search Patient",
                "12. Search Doctor",
                "13. Doctor Appointment Count",
                "14. Exit"
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
                        if (name.trim().isEmpty()) {
                            System.out.println("Invalid name.");
                            break;
                        }
                        if (system.addPatient(new Patient(id, name))) {
                            System.out.println("Patient added.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter Doctor ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Doctor Name: ");
                        String name = scanner.nextLine();
                        if (name.trim().isEmpty()) {
                            System.out.println("Invalid name.");
                            break;
                        }
                        if (system.addDoctor(new Doctor(id, name))) {
                            System.out.println("Doctor added.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
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
                        if (date == null || date.trim().isEmpty()) {
                            System.out.println("Invalid date.");
                            break;
                        }

                        if (system.addAppointment(new Appointment(patient, doctor, date))) {
                            System.out.println("Appointment added.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;

                case 4:
                    if (system.getTotalPatients() == 0) {
                        System.out.println("No patients found.");
                    } else {
                        system.listPatients();
                    }
                    break;

                case 5:
                    if (system.getTotalDoctors() == 0) {
                        System.out.println("No doctors found.");
                    } else {
                        system.listDoctors();
                    }
                    break;

                case 6:
                    if (system.getTotalAppointments() == 0) {
                        System.out.println("No appointments found.");
                    } else {
                        system.listAppointments();
                    }
                    break;

                case 7:
                    try {
                        System.out.print("Enter Patient ID to delete: ");
                        int patientId = Integer.parseInt(scanner.nextLine());
                        Patient patient = system.searchPatientById(patientId);
                        if (patient == null) {
                            System.out.println("Patient not found.");
                            break;
                        }
                        system.deletePatient(patientId);
                        System.out.println("Patient deleted.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;

                case 8:
                    try {
                        System.out.print("Enter Doctor ID to delete: ");
                        int doctorId = Integer.parseInt(scanner.nextLine());
                        Doctor doctor = system.searchDoctorById(doctorId);
                        if (doctor == null) {
                            System.out.println("Doctor not found.");
                            break;
                        }
                        system.deleteDoctor(doctorId);
                        System.out.println("Doctor deleted.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;

                case 9:
                    try {
                        System.out.print("Enter Patient ID to update: ");
                        int oldId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter New Patient ID: ");
                        int newId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter New Patient Name: ");
                        String name = scanner.nextLine();
                        if (name.trim().isEmpty()) {
                            System.out.println("Invalid name.");
                            break;
                        }
                        if (system.updatePatient(oldId, new Patient(newId, name))) {
                            System.out.println("Patient updated.");
                        } else {
                            System.out.println("Patient update failed.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 10:
                    try {
                        System.out.print("Enter Doctor ID to update: ");
                        int oldId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter New Doctor ID: ");
                        int newId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter New Doctor Name: ");
                        String name = scanner.nextLine();
                        if (name.trim().isEmpty()) {
                            System.out.println("Invalid name.");
                            break;
                        }
                        if (system.updateDoctor(oldId, new Doctor(newId, name))) {
                            System.out.println("Doctor updated.");
                        } else {
                            System.out.println("Doctor update failed.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 11:
                    try {
                        System.out.print("Enter Patient ID to search: ");
                        int patientId = Integer.parseInt(scanner.nextLine());
                        Patient patient = system.searchPatientById(patientId);
                        if (patient == null) {
                            System.out.println("Patient not found.");
                        } else {
                            System.out.println(patient);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;

                case 12:
                    try {
                        System.out.print("Enter Doctor ID to search: ");
                        int doctorId = Integer.parseInt(scanner.nextLine());
                        Doctor doctor = system.searchDoctorById(doctorId);
                        if (doctor == null) {
                            System.out.println("Doctor not found.");
                        } else {
                            System.out.println(doctor);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;

                case 13:
                    try {
                        System.out.print("Enter Doctor ID: ");
                        int doctorId = Integer.parseInt(scanner.nextLine());
                        Doctor doctor = system.searchDoctorById(doctorId);
                        if (doctor == null) {
                            System.out.println("Doctor not found.");
                            break;
                        }
                        int count = system.getAppointmentsForDoctor(doctorId);
                        System.out.println("Doctor [ID: " + doctorId + "] has " + count + " appointments.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;

                case 14:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
