package contacts.command.commands;

import contacts.command.ContactsCommand;
import contacts.controller.contactSerialization.SerializableFileManager;
import contacts.model.ContactBook;
import contacts.model.contact.Contact;
import contacts.model.contact.ContactType;
import contacts.model.contact.OrganizationContactFields;
import contacts.model.contact.newContactFactory.ContactFactory;
import contacts.model.contact.privatePerson.PrivatePersonContactFields;
import contacts.view.InputScanner;
import contacts.view.Printer;

public class EditContactCommand implements ContactsCommand {

    private Contact contact;
    private Printer printer;
    private boolean saveToFile;
    private SerializableFileManager fileManager;
    private InputScanner scanner;
    private ContactBook contactBook;

    public EditContactCommand(Contact contact, Printer printer,
                              boolean saveToFile, SerializableFileManager fileManager, InputScanner scanner,
                              ContactBook contactBook) {
        this.contact = contact;
        this.printer = printer;
        this.saveToFile = saveToFile;
        this.fileManager = fileManager;
        this.scanner = scanner;
        this.contactBook = contactBook;
    }

    @Override
    public void execute() {
        printFieldsToEdit(contact.returnTypeOfContact());
        String fieldName = scanner.readLine();
        printer.printLine("Enter " + fieldName);
        String newFieldValue = scanner.readLine();
        int editedContactIndex = contactBook.getContacts().indexOf(contact);
        Contact contactByIndex = contactBook.getContacts().get(editedContactIndex);
        contactByIndex.setFieldByFieldName(fieldName, newFieldValue);
        printer.printLine("Saved");
        if (saveToFile) {
            fileManager.writeContacts(contactBook);
        }
        printer.printLine(contactByIndex.toString());

    }


    private void printFieldsToEdit(ContactType contactType) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select a field (");
        switch (contactType) {
            case PRIVATE_PERSON: {
                for (PrivatePersonContactFields field : PrivatePersonContactFields.values()) {
                    stringBuilder.append(field.getName());
                    stringBuilder.append(", ");
                }
            }
            break;
            case ORGANIZATION: {
                for (OrganizationContactFields field : OrganizationContactFields.values()) {
                    stringBuilder.append(field.getName());
                    stringBuilder.append(", ");
                }
            }
            break;
            default:
                printer.printLine("such contact does not exist");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(" "));
        stringBuilder.append("):");
        printer.printLine(stringBuilder.toString());
    }
}
