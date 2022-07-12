package contacts.model.contact;

import java.lang.reflect.Field;
import java.util.Optional;

public class OrganizationContact extends Contact {

    private String organizationName;
    private String address;

    public OrganizationContact(String phoneNumber, String organizationName, String address) {
        super(phoneNumber);
        this.organizationName = organizationName;
        this.address = address;

    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    @Override
    public boolean setFieldByFieldName(String fieldName, String newValue) {
        Optional<OrganizationContactFields> enumFieldByName = OrganizationContactFields.findEnumFieldByName(fieldName);
        if (enumFieldByName.isPresent()) {
            OrganizationContactFields orgField = enumFieldByName.get();
            switch (orgField) {
                case PHONE_NUMBER: this.setPhoneNumber(newValue);
                break;
                case ORGANIZATION_NAME: this.setOrganizationName(newValue);
                break;
                case ADDRESS: this.setAddress(newValue);
                break;
                default: return false;
            }
            return true;
        }
        return true;
     }

    @Override
    public String getContactName() {
        return this.organizationName;
    }

    @Override
    public ContactType returnTypeOfContact() {
        return ContactType.ORGANIZATION;
    }

    @Override
    public String returnAllFieldsValues() {
        return organizationName + address + getPhoneNumber() + getCreationDate().toString() + getLastEditDate().toString();
    }

    @Override
    public String toString() {
        return "Organization name: " + organizationName + "\n" +
                "Address: " + address + "\n" +
                super.toString();

    }
}
