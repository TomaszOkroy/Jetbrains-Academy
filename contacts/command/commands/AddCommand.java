package contacts.command.commands;

import contacts.command.ContactsCommand;
import contacts.controller.contactSerialization.SerializableFileManager;
import contacts.model.ContactBook;
import contacts.model.contact.Contact;
import contacts.model.contact.ContactType;
import contacts.model.contact.newContactFactory.ContactFactory;

public class AddCommand implements ContactsCommand {
    public static final String ADD_COMMAND_NAME = "add";
    private ContactBook contactBook;
    private ContactFactory contactFactory;
    private SerializableFileManager fileManager;
    private boolean saveToFile;
    private ContactType contactType;

    public AddCommand(ContactBook contactBook, ContactFactory contactFactory,
                      SerializableFileManager fileManager, boolean saveToFile, ContactType contactType) {
        this.contactBook = contactBook;
        this.contactFactory = contactFactory;
        this.fileManager = fileManager;
        this.saveToFile = saveToFile;
        this.contactType = contactType;
    }

    @Override
    public void execute() {
        Contact newContact = contactFactory.createNewContact(contactType);
        contactBook.addContact(newContact);
        if (saveToFile) {
            fileManager.writeContacts(contactBook);
        }
    }
}
