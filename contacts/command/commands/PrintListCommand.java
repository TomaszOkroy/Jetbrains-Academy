package contacts.command.commands;

import contacts.command.ContactsCommand;
import contacts.model.ContactBook;
import contacts.model.contact.Contact;
import contacts.view.Printer;

import java.util.List;

public class PrintListCommand implements ContactsCommand {
    private final String LIST_COMMAND_NAME = "list";
    private ContactBook contactBook;
    private Printer printer;

    public PrintListCommand(ContactBook contactBook, Printer printer) {
        this.contactBook = contactBook;
        this.printer = printer;
    }

    @Override
    public void execute() {
        List<Contact> contacts = contactBook.getContacts();
        if (contacts.isEmpty()) {
            printer.printLine("The list is empty!\n");
        } else {
            int counter = 1;
            for (Contact contact : contacts) {
                printer.printLine(counter + ". " + contact.getContactName());
                counter++;
            }
            printer.printLine("");
        }

    }
}
