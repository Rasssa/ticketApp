package ticket.app.ticketApp.enums;

public enum Status {
    IN_PROGRESS("In progress"),
    FINISHED("Finished"),
    CANCELED("Canceled"),
    STOPPED("Stopped"),
    NEW("New");
    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
