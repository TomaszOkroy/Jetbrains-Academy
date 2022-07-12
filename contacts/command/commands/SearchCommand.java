package contacts.command.commands;

import contacts.command.ContactsCommand;
import contacts.model.ContactBook;
import contacts.model.contact.Contact;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCommand implements ContactsCommand {
    private static final String SEARCH_COMMAND_NAME = "search";
    private ContactBook contacts;
    private ContactBook searchedContacts;
    private String searchFraze;

    public SearchCommand(ContactBook contacts, ContactBook searchedContacts, String searchFraze) {
        this.contacts = contacts;
        this.searchedContacts = searchedContacts;
        this.searchFraze = searchFraze;
    }

    @Override
    public void execute() {
        Pattern pattern = Pattern.compile(searchFraze, Pattern.CASE_INSENSITIVE);
        for (Contact contact : contacts.getContacts()) {
            Matcher matcher = pattern.matcher(getFieldValuesFromContact(contact));
            if (matcher.find()) {
                searchedContacts.getContacts().add(contact);
            }
        }
    }


    private String getFieldValuesFromContact(Contact contact) {
        return contact.returnAllFieldsValues();
    }
}
