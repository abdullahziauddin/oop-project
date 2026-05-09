public class Appointment {

    private Patient patient;
    private Doctor doctor;
    private String date;

    public Appointment() {
    }

    public Appointment(Patient patient, Doctor doctor, String date) {
        setPatient(patient);
        setDoctor(doctor);
        setDate(date);
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor cannot be null.");
        }
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid date.");
        }
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment [Patient=" + patient.getName()
                + ", Doctor=" + doctor.getName()
                + ", Date=" + date + "]";
    }
}