package contacts.command.commands;

import contacts.command.ContactsCommand;
import contacts.model.ContactBook;
import contacts.view.Printer;

public class CountCommand implements ContactsCommand {
    private static final String COUNT_COMMAND_NAME = "count";
    private ContactBook contactBook;
    private int counter;

    public CountCommand(ContactBook contactBook) {
        this.contactBook = contactBook;
    }

    @Override
    public void execute() {
        counter = contactBook.getContacts().size();
    }

    public int getCounter() {
        return counter;
    }
}
