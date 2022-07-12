package contacts.model;

import contacts.model.contact.Contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Contact> contacts = new ArrayList<>();

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Contact getContactByIndex(int index) {
       return contacts.get(index - 1);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }


}
