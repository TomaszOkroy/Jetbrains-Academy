package contacts.model.contact.newContactFactory;

import contacts.model.contact.Contact;
import contacts.model.contact.ContactType;
import contacts.model.contact.OrganizationContact;
import contacts.model.contact.privatePerson.Gender;
import contacts.model.contact.privatePerson.PrivatePersonContact;
import contacts.model.validation.Validator;
import contacts.view.InputScanner;
import contacts.view.Printer;

import java.util.Optional;

public class NewContactFactory extends ContactFactory{

    private InputScanner scanner;
    private Printer printer;
    private Validator phoneNumberValidator;
    private Validator genderValidator;
    private Validator birthDateValidator;

    public NewContactFactory(InputScanner scanner, Printer printer, Validator phoneNumberValidator,
                             Validator genderValidator, Validator birthDateValidator) {
        this.scanner = scanner;
        this.printer = printer;
        this.phoneNumberValidator = phoneNumberValidator;
        this.genderValidator = genderValidator;
        this.birthDateValidator = birthDateValidator;
    }

    @Override
    protected Optional<Contact> createContact(ContactType type) {
        switch (type) {
            case PRIVATE_PERSON: return Optional.of(createPrivatePersonContact());
            case ORGANIZATION: return Optional.of(createOrganizationContact());
            default: return Optional.empty();
        }

    }

    private Contact createPrivatePersonContact() {
        printer.printLine("Enter the name:");
        String name = scanner.readLine();
        printer.printLine("Enter the surname:");
        String surname = scanner.readLine();
        printer.printLine("Enter the birth date:");
        String birthDate = validateBirthDateFromUser();
        printer.printLine("Enter the gender (M, F):");
        Gender gender = validateGenderFromUser();
        printer.printLine("Enter the number:");
        String phoneNumber = validatePhoneNumberFromUser();

        return new PrivatePersonContact(phoneNumber, name, surname, birthDate, gender);
    }

    private Contact createOrganizationContact() {
        printer.printLine("Enter the organization name:");
        String organizationName = scanner.readLine();
        printer.printLine("Enter the address:");
        String address = scanner.readLine();
        printer.printLine("Enter the number:");
        String organizationPhoneNumber = validatePhoneNumberFromUser();
        return new OrganizationContact(organizationPhoneNumber, organizationName, address);
    }

    private String validatePhoneNumberFromUser() {
        String phoneNumber = scanner.readLine();
        if (!phoneNumberValidator.validate(phoneNumber)) {
            printer.printLine("Wrong number format!");
            phoneNumber = "[no number]";
        }
        return phoneNumber;
    }

    private String validateBirthDateFromUser() {
        String birthdate = scanner.readLine();
        if (!birthDateValidator.validate(birthdate)) {
            return birthdate;
        }
        printer.printLine("Bad birth date!");
        return "[no data]";
    }

    private Gender validateGenderFromUser() {
        String gender = scanner.readLine();
        if (!birthDateValidator.validate(gender)) {
            return Gender.findByName(gender);
        }
        printer.printLine("Bad gender!");
        return null;
    }
}
