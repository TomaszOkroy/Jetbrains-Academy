package contacts.model.menu;

public enum RecordOptions {
    EDIT("edit"), DELETE("delete"), MENU("menu");

    private String name;

    RecordOptions(String name) {
        this.name = name;
    }

    public static void printSearchOptions() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[record] Enter action (")
                .append(EDIT.name)
                .append(", ")
                .append(DELETE.name)
                .append(", ")
                .append(MENU.name)
                .append("):");
        System.out.println(stringBuilder);

    }

    public static RecordOptions findByName(String name) {
        return RecordOptions.valueOf(name.toUpperCase());
    }
}
