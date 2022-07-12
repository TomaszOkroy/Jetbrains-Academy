package contacts.command.commands;

import contacts.command.ContactsCommand;
import contacts.model.ContactBook;
import contacts.model.contact.Contact;
import contacts.view.Printer;

public class PrintContactCommand implements ContactsCommand {

    private Printer printer;
    private Contact contact;

    public PrintContactCommand(Printer printer, Contact contact) {
        this.printer = printer;
        this.contact = contact;
    }

    @Override
    public void execute() {
        printer.printLine(contact.toString());
    }




}
