package contacts.model.contact.privatePerson;

import contacts.model.contact.Contact;
import contacts.model.contact.ContactType;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Optional;

public class PrivatePersonContact extends Contact {

    private String name;
    private String surname;
    private String birthDate;
    private Gender gender;

    public PrivatePersonContact(String phoneNumber, String name, String surname, String birthDate, Gender gender) {
        super(phoneNumber);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }



    @Override
    public boolean setFieldByFieldName(String fieldName, String newValue) {
        Optional<PrivatePersonContactFields> fieldByName = PrivatePersonContactFields.findFieldByName(fieldName);
        if (fieldByName.isPresent()) {
            switch (fieldByName.get()) {
                case PHONE_NUMBER: setPhoneNumber(newValue);
                break;
                case GENDER: setGender(Gender.findByName(newValue));
                break;
                case SURNAME: setSurname(newValue);
                break;
                case NAME: setName(newValue);
                break;
                case BIRTH_DATE: setBirthDate(newValue);
                break;
                default: return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public String getContactName() {
        return this.getName() + " " + this.getSurname();
    }

    @Override
    public ContactType returnTypeOfContact() {
        return ContactType.PRIVATE_PERSON;
    }

    @Override
    public String returnAllFieldsValues() {
        String gender = this.gender == null ? "[no data]" : this.gender.getName();
        return name + surname + birthDate + gender + getPhoneNumber() +
                getCreationDate().toString() + getLastEditDate().toString();
    }

    @Override
    public String toString() {
        String gender = this.gender == null ? "[no data]" : this.gender.getName();

        return "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Birth date: " + birthDate + "\n" +
                "Gender: " + gender + "\n" +
                super.toString();
    }
}
