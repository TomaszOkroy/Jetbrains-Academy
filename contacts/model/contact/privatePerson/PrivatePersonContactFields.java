package contacts.model.contact.privatePerson;

import java.util.Optional;

public enum PrivatePersonContactFields {
    NAME("name"), SURNAME("surname"),BIRTH_DATE("birth"), GENDER("gender"), PHONE_NUMBER("number");

    private String name;

    PrivatePersonContactFields(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<PrivatePersonContactFields> findFieldByName(String name) {
        for (PrivatePersonContactFields field : PrivatePersonContactFields.values()) {
            if (name.equals(field.getName())) {
                return Optional.of(field);
            }
        }
        return Optional.empty();
    }
}
