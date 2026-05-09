public class Doctor {

    private int id;
    private String name;

    public Doctor() {
    }

    public Doctor(int id, String name) {
        setId(id);
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid doctor ID.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid doctor name.");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "Doctor [ID=" + id + ", Name=" + name + "]";
    }
}