# Clinic Appointment System

Console-oriented **clinic management** coursework: register patients and doctors, book appointments, and keep the schedule consistent. This repository holds the **core domain logic** in `ClinicSystem`—no menus, no file I/O, no extra frameworks.

---

## Highlights

| Concern | Behavior |
|--------|----------|
| **Storage** | `ArrayList` for patients, doctors, and appointments |
| **Double booking** | Same doctor + same date → appointment is rejected |
| **Invalid appointments** | Missing patient or doctor → error line, nothing stored |
| **Referential cleanup** | Deleting a patient or doctor removes their appointments |

---

## Requirements

- **Java** (JDK 17+ recommended; JDK 21/25 work fine)
- Plain `javac` / `java` — no Maven/Gradle required for the core class

---

## Project layout

```
oop-project/
├── ClinicSystem.java   # Core logic (your submission focus)
├── README.md
└── .gitignore          # Ignores *.class
```

Other coursework files (e.g. `Patient.java`, `Doctor.java`, `Appointment.java`, `Main.java`) are expected to live **alongside** `ClinicSystem` in the same package when you build the full app.

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

- `addPatient(Patient p)` / `addDoctor(Doctor d)` — append to the registry  
- `addAppointment(Appointment a)` — validates, prevents double booking, then stores  

**Queries**

- `searchPatientById(int id)` / `searchDoctorById(int id)` — `null` if not found  
- `getTotalPatients()` / `getTotalAppointments()`  

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
