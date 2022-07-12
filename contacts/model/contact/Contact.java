package contacts.model.contact;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Contact implements Serializable {

    private static final long serialVersionUID = 3812017177088226528L;

    private String phoneNumber;
    private LocalDateTime creationDate;
    private LocalDateTime lastEditDate;

    public Contact(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.creationDate = LocalDateTime.now().withSecond(0).withNano(0);
        this.lastEditDate = LocalDateTime.now().withSecond(0).withNano(0);
    }


    public abstract boolean setFieldByFieldName(String fieldName, String newValue);
    public abstract String getContactName();
    public abstract ContactType returnTypeOfContact();
    public abstract String returnAllFieldsValues();

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(LocalDateTime lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    @Override
    public String toString() {
        return "Number: " + phoneNumber + "\n" +
                "Time created: " + creationDate + "\n" +
                "Time last edit: " + lastEditDate + "\n";
    }
}
