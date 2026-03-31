import java.util.*;
import java.io.*;

public class LogisticsManager {
    private static List<EventResource> inventory = new ArrayList<>();
    private static List<VolunteerTask> tasks = new ArrayList<>();
    private static final String DATA_FILE = "event_data.dat";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();
        System.out.println("===============================================");
        System.out.println("   EVENTFLOW: CAMPUS LOGISTICS SYSTEM v1.0     ");
        System.out.println("===============================================");

        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. View Inventory");
            System.out.println("2. Add New Resource");
            System.out.println("3. View Task List");
            System.out.println("4. Assign New Task");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. Save & Exit");
            System.out.print("\nSelect an option: ");

            try {
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1": displayInventory(); break;
                    case "2": addNewResource(); break;
                    case "3": displayTasks(); break;
                    case "4": addNewTask(); break;
                    case "5": completeTask(); break;
                    case "6": saveData(); System.exit(0);
                    default: System.out.println("Error: Invalid selection.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addNewResource() {
        System.out.print("Enter Item Name (e.g., Guest Memento): ");
        String name = scanner.nextLine();
        System.out.print("Enter Category (e.g., Hospitality): ");
        String cat = scanner.nextLine();
        System.out.print("Enter Initial Quantity: ");
        int qty = Integer.parseInt(scanner.nextLine());
        inventory.add(new EventResource(name, qty, cat));
        System.out.println("Resource added to inventory.");
    }

    private static void addNewTask() {
        System.out.print("Task Description (e.g., Pick up Guest): ");
        String desc = scanner.nextLine();
        System.out.print("Assign to Volunteer: ");
        String name = scanner.nextLine();
        tasks.add(new VolunteerTask(desc, name));
        System.out.println("Task assigned successfully.");
    }

    private static void displayInventory() {
        System.out.println("\n--- CURRENT RESOURCES ---");
        if (inventory.isEmpty()) System.out.println("No items logged.");
        else inventory.forEach(System.out::println);
    }

    private static void displayTasks() {
        System.out.println("\n--- VOLUNTEER DUTY ROSTER ---");
        if (tasks.isEmpty()) System.out.println("No tasks assigned.");
        else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ". " + tasks.get(i));
            }
        }
    }

    private static void completeTask() {
        displayTasks();
        if (tasks.isEmpty()) return;
        System.out.print("Enter Task Index to complete: ");
        int index = Integer.parseInt(scanner.nextLine());
        tasks.get(index).markAsDone();
        System.out.println("Task updated.");
    }

    // --- DATA PERSISTENCE ---
    private static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(inventory);
            oos.writeObject(tasks);
            System.out.println("Event data synced to disk.");
        } catch (IOException e) {
            System.out.println("Critical Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            inventory = (List<EventResource>) ois.readObject();
            tasks = (List<VolunteerTask>) ois.readObject();
        } catch (Exception e) {
            System.out.println("System initialized. Ready for new event data.");
        }
    }
}
