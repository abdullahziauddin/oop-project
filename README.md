# Clinic Appointment System

Console-oriented **clinic management** coursework: register patients and doctors, book appointments, and keep the schedule consistent.

---

## Highlights

| Concern | Behavior |
|--------|----------|
| **Storage** | `ArrayList` for patients, doctors, and appointments |
| **Double booking** | Same doctor + same date → appointment is rejected |
| **Invalid appointments** | Missing patient/doctor/date or duplicates are rejected with clear messages |
| **Referential cleanup** | Deleting a patient or doctor removes their appointments |
| **Aggregation** | Count appointments per doctor by doctor ID |

---

## Requirements

- **Java** (JDK 17+ recommended; JDK 21/25 work fine)
- Plain `javac` / `java` — no Maven/Gradle required for the core class

---

## Project layout

```
oop-project/
├── ClinicSystem.java   # Abdullah scope: core business logic
├── Main.java           # Ibrahim scope: menu + input handling
├── README.md
└── .gitignore          # Ignores *.class
```

Other coursework files (e.g. `Patient.java`, `Doctor.java`, `Appointment.java`) are expected to live **alongside** these files in the same package when you build the full app.

---

## Team scope summary

- **Abdullah (`ClinicSystem.java`)**
  - In-memory storage with `ArrayList` for patients, doctors, and appointments
  - Validation for nulls and duplicate IDs
  - Appointment conflict prevention (same doctor + same date)
  - Search, list, update, delete, totals, and per-doctor appointment count
  - Referential cleanup of appointments on patient/doctor delete

- **Ibrahim (`Main.java`)**
  - Console menu loop with `String[]` menu options
  - Input parsing via `Scanner` + `try/catch` for numeric fields
  - Calls `ClinicSystem` methods only (no duplicated business logic)
  - User flows for add/list/search/update/delete and doctor appointment count
  - Clear success/failure and empty-state output messages

---

## Model contract

`ClinicSystem` assumes teammates implement these accessors consistently:

| Type | Required API |
|------|----------------|
| `Patient` | `int getId()` |
| `Doctor` | `int getId()` |
| `Appointment` | `Patient getPatient()`, `Doctor getDoctor()`, `String getDate()` |

**Date:** `getDate()` must return a `String` (e.g. `"2026-04-22"` or `"Mon 9am"`). Matching uses `Objects.equals`, so two strings must agree exactly to be the “same” slot.

**Listings:** `listPatients`, `listDoctors`, and `listAppointments` call `System.out.println` on each object—override `toString()` on your models for readable output.

---

## Build & run

From the project root (after all `.java` sources exist):

```bash
javac *.java
java Main
```

Remove stray bytecode if needed:

```bash
rm -f *.class
```

---

## Public API (summary)

**Mutators**

- `addPatient(Patient p)` / `addDoctor(Doctor d)` — validate and return success status (`boolean`)  
- `addAppointment(Appointment a)` — validates, prevents double booking, then stores  

**Queries**

- `searchPatientById(int id)` / `searchDoctorById(int id)` — `null` if not found  
- `getTotalPatients()` / `getTotalDoctors()` / `getTotalAppointments()`  
- `getAppointmentsForDoctor(int doctorId)`  

**Removal**

- `deletePatient(int id)` / `deleteDoctor(int id)` — removes the entity **and** dependent appointments  

**Listing**

- `listPatients()` / `listDoctors()` / `listAppointments()` — one `println` per row  

---

## Design notes

- **Single responsibility:** `ClinicSystem` only manages in-memory collections and rules; UI belongs elsewhere.  
- **Encapsulation:** Lists are private; collaborators interact through methods only.  
- **No static global state:** one `ClinicSystem` instance per application is the natural lifecycle.

---

## Contributing (team)

1. Keep the **model contract** stable, or update this README when it changes.  
2. Do not commit `*.class` files (already ignored).  
3. Run `javac *.java` before opening a PR so everyone stays green.

---

## License

Educational / coursework use unless otherwise specified by your institution.
