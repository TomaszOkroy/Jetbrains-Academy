package contacts;

import contacts.controller.ContactController;
import contacts.controller.ContactsMainController;
import contacts.controller.MainController;
import contacts.controller.contactSerialization.SerializableFileManager;
import contacts.model.ContactBook;
import contacts.model.contact.Contact;
import contacts.model.contact.OrganizationContact;
import contacts.model.menu.MainMenu;
import contacts.view.InputScanner;
import contacts.view.Printer;

import java.util.List;


public class Main {
    public static void main(String[] args) {
//        Printer printer = new Printer();
//        InputScanner inputScanner = new InputScanner();
//        MainMenu mainMenu = new MainMenu(printer);
//        ContactController contactController = new ContactController(inputScanner, printer, args);
//        MainController mainController = new MainController(mainMenu, inputScanner, printer, contactController);
//        mainController.mainLoop();

        ContactsMainController contactsMainController = new ContactsMainController(args);
        contactsMainController.mainLoop();


    }


}
