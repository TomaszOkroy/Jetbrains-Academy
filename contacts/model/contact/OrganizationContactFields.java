package contacts.model.contact;

import java.util.Optional;

public enum OrganizationContactFields {
    ORGANIZATION_NAME("name"), ADDRESS("address"), PHONE_NUMBER("number");

    private String name;

    OrganizationContactFields(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<OrganizationContactFields> findEnumFieldByName(String name) {
        for (OrganizationContactFields field : OrganizationContactFields.values()) {
            if (name.equals(field.getName())) {
                return Optional.of(field);
            }
        }
        return Optional.empty();
    }
}
