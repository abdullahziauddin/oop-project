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
        if (patient == null) {
            System.out.println("Error: patient cannot be null.");
            return;
        }
        if (findPatientById(patient.getId()) != null) {
            System.out.println("Error: patient ID already exists.");
            return;
        }
        patients.add(patient);
    }

    /** Adds a doctor to the system. */
    public void addDoctor(Doctor doctor) {
        if (doctor == null) {
            System.out.println("Error: doctor cannot be null.");
            return;
        }
        if (findDoctorById(doctor.getId()) != null) {
            System.out.println("Error: doctor ID already exists.");
            return;
        }
        doctors.add(doctor);
    }

    /**
     * Adds an appointment if patient and doctor are set, and the doctor is not
     * already booked on that date.
     */
    public boolean addAppointment(Appointment appointment) {
        if (appointment == null) {
            System.out.println("Error: appointment cannot be null.");
            return false;
        }
        if (appointment.getPatient() == null || appointment.getDoctor() == null) {
            System.out.println("Error: appointment must have a patient and a doctor.");
            return false;
        }
        if (hasDoctorBookingOnDate(appointment.getDoctor(), appointment.getDate())) {
            System.out.println("Error: doctor is already booked on that date.");
            return false;
        }
        appointments.add(appointment);
        return true;
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

    /** Updates the patient record for this id, preserving unique ID constraints. */
    public boolean updatePatient(int id, Patient updatedPatient) {
        if (updatedPatient == null) {
            System.out.println("Error: updated patient cannot be null.");
            return false;
        }
        int index = findPatientIndexById(id);
        if (index == -1) {
            System.out.println("Error: patient not found.");
            return false;
        }
        Patient conflict = findPatientById(updatedPatient.getId());
        if (conflict != null && conflict.getId() != id) {
            System.out.println("Error: patient ID already exists.");
            return false;
        }
        patients.set(index, updatedPatient);
        return true;
    }

    /** Updates the doctor record for this id, preserving unique ID constraints. */
    public boolean updateDoctor(int id, Doctor updatedDoctor) {
        if (updatedDoctor == null) {
            System.out.println("Error: updated doctor cannot be null.");
            return false;
        }
        int index = findDoctorIndexById(id);
        if (index == -1) {
            System.out.println("Error: doctor not found.");
            return false;
        }
        Doctor conflict = findDoctorById(updatedDoctor.getId());
        if (conflict != null && conflict.getId() != id) {
            System.out.println("Error: doctor ID already exists.");
            return false;
        }
        doctors.set(index, updatedDoctor);
        return true;
    }

    /** Returns how many patients are stored. */
    public int getTotalPatients() {
        return patients.size();
    }

    /** Returns how many appointments are stored. */
    public int getTotalAppointments() {
        return appointments.size();
    }

    /** Returns how many doctors are stored. */
    public int getTotalDoctors() {
        return doctors.size();
    }

    /** True if this doctor already has an appointment on the given date. */
    private boolean hasDoctorBookingOnDate(Doctor doctor, String date) {
        String normalizedDate = normalizeDate(date);
        for (Appointment existing : appointments) {
            if (existing.getDoctor() == null) {
                continue;
            }
            if (existing.getDoctor().getId() == doctor.getId()
                    && Objects.equals(normalizeDate(existing.getDate()), normalizedDate)) {
                return true;
            }
        }
        return false;
    }

    /** Normalizes date text to reduce false mismatches while staying String-based. */
    private String normalizeDate(String date) {
        if (date == null) {
            return null;
        }
        return date.trim().replaceAll("\\s+", " ").toLowerCase();
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

    private int findPatientIndexById(int id) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private int findDoctorIndexById(int id) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
