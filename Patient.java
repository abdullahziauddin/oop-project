public class Patient {

    private int id;
    private String name;

    // constructeur par defaut
    public Patient() {
    }

    // Constructeur avec parametres (overloaded)
    public Patient(int id, String name) {
        setId(id);
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid patient ID.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid patient name.");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "Patient [ID=" + id + ", Name=" + name + "]";
    }
}