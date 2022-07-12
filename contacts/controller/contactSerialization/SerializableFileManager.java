package contacts.controller.contactSerialization;

import contacts.model.ContactBook;

import java.io.*;

public class SerializableFileManager {

    private File file;
    private String[] command;

    public SerializableFileManager(File file) {
        this.file = file;
    }

    public ContactBook readContacts() {
        try (
                FileInputStream inputStream =  new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        ) {
            return (ContactBook) objectInputStream.readObject();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean writeContacts(ContactBook contactBook) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(contactBook);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
