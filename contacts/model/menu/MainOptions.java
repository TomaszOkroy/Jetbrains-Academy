package contacts.model.menu;

import java.util.Locale;
import java.util.Optional;

public enum MainOptions {
    ADD, LIST, SEARCH, COUNT, EXIT;

    public static Optional<MainOptions> findOption(String lineFromUser) {
        for (MainOptions value : MainOptions.values()) {
            if (value.name().equalsIgnoreCase(lineFromUser)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }

    public static void printMainOptions() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[menu] ");
        stringBuilder.append("Enter action (");
        for (MainOptions option : MainOptions.values()) {
            stringBuilder.append(option.name().toLowerCase(Locale.ROOT));
            stringBuilder.append(", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(" "));
        stringBuilder.append("):");
        System.out.println(stringBuilder);

    }
}
