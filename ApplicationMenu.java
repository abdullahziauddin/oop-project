import java.io.PrintStream;
import java.util.Arrays;
import java.util.Objects;

/**
 * Console menu lines for the clinic app. Keeps option text in one place and
 * retains a {@code String[]} for coursework array requirements.
 */
public class ApplicationMenu {

    private final String[] items;

    /** Default clinic menu (14 options). */
    public ApplicationMenu() {
        this.items = new String[] {
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
    }

    /**
     * Overloaded constructor for a custom menu (copy is stored; caller's array
     * can be mutated afterward without affecting this menu).
     */
    public ApplicationMenu(String[] customItems) {
        if (customItems == null || customItems.length == 0) {
            throw new IllegalArgumentException("Menu items cannot be null or empty.");
        }
        for (String line : customItems) {
            if (line == null || line.trim().isEmpty()) {
                throw new IllegalArgumentException("Each menu line must be non-empty.");
            }
        }
        this.items = Arrays.copyOf(customItems, customItems.length);
    }

    /** Prints every menu line to the given stream (typically {@code System.out}). */
    public void printLines(PrintStream out) {
        Objects.requireNonNull(out, "out");
        for (String option : items) {
            out.println(option);
        }
    }

    /** Number of menu entries (used with numeric menu choice 1..n). */
    public int optionCount() {
        return items.length;
    }

    /** Raw lines (defensive copy). */
    public String[] getItemsCopy() {
        return Arrays.copyOf(items, items.length);
    }
}
