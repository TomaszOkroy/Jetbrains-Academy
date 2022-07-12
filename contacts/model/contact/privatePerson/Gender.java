package contacts.model.contact.privatePerson;

public enum Gender {
    MALE("M"), FEMALE("F");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    public static Gender findByName(String name) {
        if (name.equals(Gender.MALE.getName())) {
            return MALE;
        }
        return FEMALE;
    }

    public String getName() {
        return name;
    }
}
