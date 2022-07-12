package contacts.model.menu;

import contacts.model.validation.SearchOptionValidation;

public enum SearchOptions {
    NUMBER("number"), BACK("back"), AGAIN("again");

    private String name;

    SearchOptions(String name) {
        this.name = name;
    }


    public static void printSearchOptions() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[search] Enter action ([").append(NUMBER.name)
                .append("], ")
                .append(BACK.name)
                .append(", ")
                .append(AGAIN.name)
                .append("):");
        System.out.println(stringBuilder);

    }

    public static SearchOptions findByName(String optionName) {
        for (SearchOptions value : SearchOptions.values()) {
            if (optionName.equals(value.name)) {
                return value;
            }
        }
        return null;
    }
}
