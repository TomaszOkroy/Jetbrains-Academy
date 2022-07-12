package contacts.command;

public class ContactBookController {
    private ContactsCommand command;

    public void setCommand(ContactsCommand command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}
