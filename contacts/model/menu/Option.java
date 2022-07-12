package contacts.model.menu;

import java.util.Optional;

public enum Option {
    ADD, REMOVE, EDIT, COUNT, INFO, EXIT;

    public static Optional<Option> findOption(String lineFromUser) {
        for (Option value : Option.values()) {
            if (value.name().equalsIgnoreCase(lineFromUser)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
