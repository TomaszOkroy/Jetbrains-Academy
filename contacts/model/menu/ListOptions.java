package contacts.model.menu;

public enum ListOptions {
    NUMBER("number"), BACK("back");

    private String name;

    ListOptions(String name) {
        this.name = name;
    }

    public static void printSearchOptions() {
        String stringBuilder = "[list] Enter action ([" +
                NUMBER.name +
                "], " +
                BACK.name +
                "):";
        System.out.println(stringBuilder);

    }

    public static ListOptions findByName(String name) {
        for (ListOptions value : ListOptions.values()) {
            if (name.equals(value.name)) {
                return value;
            }
        }
        return null;
    }
}
