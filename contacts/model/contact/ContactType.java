package contacts.model.contact;

public enum ContactType {
    PRIVATE_PERSON("person"), ORGANIZATION("organization");

    private String name;

    ContactType(String name) {
        this.name = name;
    }

    public static ContactType findByName(String name) {
        for (ContactType value : ContactType.values()) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
