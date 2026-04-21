import java.util.ArrayList;
import java.util.Objects;

/**
 * Core registry for patients, doctors, and appointments.
 * No console menus; listing methods print one line per record.
 */
public class ClinicSystem {

    private final ArrayList<Patient> patients;
    private final ArrayList<Doctor> doctors;
    private final ArrayList<Appointment> appointments;

    /** Creates empty lists for all entity types. */
    public ClinicSystem() {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    /** Adds a patient to the system. */
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    /** Adds a doctor to the system. */
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    /**
     * Adds an appointment if patient and doctor are set, and the doctor is not
     * already booked on that date.
     */
    public void addAppointment(Appointment appointment) {
        if (appointment.getPatient() == null || appointment.getDoctor() == null) {
            System.out.println("Error: appointment must have a patient and a doctor.");
            return;
        }
        if (hasDoctorBookingOnDate(appointment.getDoctor(), appointment.getDate())) {
            System.out.println("Error: doctor is already booked on that date.");
            return;
        }
        appointments.add(appointment);
    }

    /** Prints each patient on its own line (uses {@link Object#toString()}). */
    public void listPatients() {
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    /** Prints each doctor on its own line (uses {@link Object#toString()}). */
    public void listDoctors() {
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }

    /** Prints each appointment on its own line (uses {@link Object#toString()}). */
    public void listAppointments() {
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    /** Returns the patient with the given id, or null if none exists. */
    public Patient searchPatientById(int id) {
        return findPatientById(id);
    }

    /** Returns the doctor with the given id, or null if none exists. */
    public Doctor searchDoctorById(int id) {
        return findDoctorById(id);
    }

    /** Removes the patient and any appointments linked to that patient. */
    public void deletePatient(int id) {
        Patient found = findPatientById(id);
        if (found != null) {
            removeAppointmentsForPatientId(id);
            patients.remove(found);
        }
    }

    /** Removes the doctor and any appointments linked to that doctor. */
    public void deleteDoctor(int id) {
        Doctor found = findDoctorById(id);
        if (found != null) {
            removeAppointmentsForDoctorId(id);
            doctors.remove(found);
        }
    }

    /** Returns how many patients are stored. */
    public int getTotalPatients() {
        return patients.size();
    }

    /** Returns how many appointments are stored. */
    public int getTotalAppointments() {
        return appointments.size();
    }

    /** True if this doctor already has an appointment on the given date. */
    private boolean hasDoctorBookingOnDate(Doctor doctor, String date) {
        for (Appointment existing : appointments) {
            if (existing.getDoctor() == null) {
                continue;
            }
            if (existing.getDoctor().getId() == doctor.getId()
                    && Objects.equals(existing.getDate(), date)) {
                return true;
            }
        }
        return false;
    }

    /** Drops appointments that reference the patient id (e.g. after patient removed). */
    private void removeAppointmentsForPatientId(int patientId) {
        appointments.removeIf(
                a -> a.getPatient() != null && a.getPatient().getId() == patientId);
    }

    /** Drops appointments that reference the doctor id (e.g. after doctor removed). */
    private void removeAppointmentsForDoctorId(int doctorId) {
        appointments.removeIf(
                a -> a.getDoctor() != null && a.getDoctor().getId() == doctorId);
    }

    private Patient findPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    private Doctor findDoctorById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null;
    }
}
