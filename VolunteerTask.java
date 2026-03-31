import java.io.Serializable;

/**
 * Tracks logistics tasks assigned to student volunteers.
 */
public class VolunteerTask implements Serializable {
    private String taskDescription;
    private String assignedTo;
    private boolean isCompleted;

    public VolunteerTask(String taskDescription, String assignedTo) {
        this.taskDescription = taskDescription;
        this.assignedTo = assignedTo;
        this.isCompleted = false;
    }

    public void markAsDone() { this.isCompleted = true; }

    @Override
    public String toString() {
        String status = isCompleted ? "[COMPLETED]" : "[ PENDING ]";
        return String.format("%s %-25s | Lead: %s", status, taskDescription, assignedTo);
    }
}