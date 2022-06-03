package ticket.app.ticketApp.enums;

public enum Category {
    HARDWARE("Problem with hardware"),
    APPLICATION("Problem with application"),
    SYSTEM("Problem with system"),
    OTHER("Other");
    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
