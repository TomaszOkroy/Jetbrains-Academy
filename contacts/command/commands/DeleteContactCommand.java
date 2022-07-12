package contacts.command.commands;

import contacts.command.ContactsCommand;
import contacts.controller.contactSerialization.SerializableFileManager;
import contacts.model.ContactBook;
import contacts.model.contact.Contact;
import contacts.view.InputScanner;
import contacts.view.Printer;

public class DeleteContactCommand implements ContactsCommand {
    private Contact contactToDelete;
    private Printer printer;
    private boolean saveToFile;
    private SerializableFileManager fileManager;
    private ContactBook contactBook;

    public DeleteContactCommand(Contact contactToDelete, Printer printer, boolean saveToFile,
                                SerializableFileManager fileManager, ContactBook contactBook) {
        this.contactToDelete = contactToDelete;
        this.printer = printer;
        this.saveToFile = saveToFile;
        this.fileManager = fileManager;
        this.contactBook = contactBook;
    }

    @Override
    public void execute() {
        contactBook.getContacts().remove(contactToDelete);
        printer.printLine("The record removed!\n");
        if (saveToFile) {
            fileManager.writeContacts(contactBook);
        }

    }


}
