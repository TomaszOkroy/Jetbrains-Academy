package contacts.model.contact.newContactFactory;

import contacts.model.contact.Contact;
import contacts.model.contact.ContactType;

import java.util.Optional;

public abstract class ContactFactory {

    protected abstract Optional<Contact> createContact(ContactType type);


    // change that awful code!!!
    public Contact createNewContact(ContactType type) {
        Optional<Contact> contact = createContact(type);
        return contact.orElse(null);
    }

}
