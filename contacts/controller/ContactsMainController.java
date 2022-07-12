package contacts.controller;

import contacts.command.ContactBookController;
import contacts.command.commands.*;
import contacts.controller.contactSerialization.SerializableFileManager;
import contacts.model.ContactBook;
import contacts.model.contact.Contact;
import contacts.model.contact.ContactType;
import contacts.model.contact.newContactFactory.ContactFactory;
import contacts.model.contact.newContactFactory.NewContactFactory;
import contacts.model.menu.*;
import contacts.model.validation.BirthDateValidator;
import contacts.model.validation.GenderValidator;
import contacts.model.validation.PhoneNumberValidator;
import contacts.model.validation.SearchOptionValidation;
import contacts.view.InputScanner;
import contacts.view.Printer;


import java.io.File;
import java.util.Optional;

public class ContactsMainController {
    private InputScanner scanner;
    private Printer printer;


    private ContactBook contactBook;
    private SerializableFileManager fileManager;
    private ContactFactory contactFactory;
    private boolean saveToFile;
    private ContactBookController contactsController;

    public ContactsMainController(String[] commands) {
        this.scanner = new InputScanner();
        this.printer = new Printer();
        PhoneNumberValidator phoneValidator = new PhoneNumberValidator();
        GenderValidator genderValidator= new GenderValidator();
        BirthDateValidator birthValidator= new BirthDateValidator();
        this.contactFactory = new NewContactFactory(scanner, printer, phoneValidator, genderValidator, birthValidator);
        this.contactBook = initializeContactBookFile(commands);
        this.contactsController = new ContactBookController();

    }

    public void mainLoop() {
        MainOptions optionFromUser;

        do {
            MainOptions.printMainOptions();
            optionFromUser = getOptionFromUser();
            executeOption(optionFromUser);

        } while (optionFromUser != MainOptions.EXIT);

    }

    private void executeOption(MainOptions optionFromUser) {
        switch (optionFromUser) {
            case ADD:
                addContact();
                break;
            case SEARCH:
                searchContactLoop();
                break;
            case LIST:
                listLoop();
                break;
            case COUNT:
                countContacts();
        }
    }

    private void countContacts() {
        CountCommand countCommand = new CountCommand(contactBook);
        contactsController.setCommand(countCommand);
        contactsController.executeCommand();
        printer.printLine(String.format("The Phone Book has %d records.\n", countCommand.getCounter()));
    }

    private void listLoop() {
        PrintListCommand listCommand = new PrintListCommand(contactBook, printer);
        contactsController.setCommand(listCommand);
        contactsController.executeCommand();
        if (contactBook.getContacts().size() > 0) {
            ListOptions.printSearchOptions();
            String userOption = scanner.readLine();
            ListOptions option = ListOptions.findByName(userOption);
            if (SearchOptionValidation.isNumberInrRange(contactBook, userOption)) {
                option = ListOptions.NUMBER;
            }
            switch (option) {
                case NUMBER: {
                    Contact contact = contactBook.getContactByIndex(Integer.parseInt(userOption));
                    printContact(contact);
                    recordContactLoop(contact);
                }
                break;
                case BACK:
                    break;
                default:
                    printer.printLine("no such option");

            }
        }

    }

    private void searchContactLoop() {
        if (contactBook.getContacts().size() > 0) {
            ContactBook searchedContacts = searchAndPrint();
            SearchOptions option;
            boolean exitLoop = false;
            do {

                SearchOptions.printSearchOptions();
                String userOption = scanner.readLine();
                option = SearchOptions.findByName(userOption);
                if (SearchOptionValidation.isNumberInrRange(contactBook, userOption)) {
                    option = SearchOptions.NUMBER;
                }
                switch (option) {
                    case AGAIN:
                        searchedContacts = searchAndPrint();
                        break;
                    case BACK:
                        exitLoop = true;
                        break;
                    case NUMBER: {
                        Contact contactToPrint = searchedContacts.getContactByIndex(Integer.parseInt(userOption));
                        printContact(contactToPrint);
                        recordContactLoop(contactToPrint);
                        exitLoop = true;
                    }
                    break;
                    default:
                        printer.printLine("no such option");
                }
            } while (!exitLoop);
        } else {
            printer.printLine("No contacts to search\n");
        }

    }

    private void recordContactLoop(Contact contactToEdit) {
        RecordOptions option;
        boolean exitLoop = false;
        do {
            RecordOptions.printSearchOptions();
            String userOption = scanner.readLine();
            option = RecordOptions.findByName(userOption);
            switch (option) {
                case EDIT:
                    editContact(contactToEdit);
                    break;
                case DELETE:
                    deleteContact(contactToEdit);
                    exitLoop = true;
                    break;
                case MENU:
                    exitLoop = true;
                    break;
                default:
                    printer.printLine("such command does not exists!");
            }


        } while (!exitLoop);

    }

    private void deleteContact(Contact contactToDelete) {
        DeleteContactCommand deleteCommand = new DeleteContactCommand(contactToDelete, printer, saveToFile, fileManager,
                contactBook);
        contactsController.setCommand(deleteCommand);
        contactsController.executeCommand();
    }

    private void editContact(Contact contactToEdit) {
        EditContactCommand editCommand =
                new EditContactCommand(contactToEdit, printer, saveToFile, fileManager, scanner, contactBook);
        contactsController.setCommand(editCommand);
        contactsController.executeCommand();

    }

    private void printContact(Contact contact) {
        PrintContactCommand printContactCommand = new PrintContactCommand(printer, contact);
        contactsController.setCommand(printContactCommand);
        contactsController.executeCommand();
    }

    private ContactBook searchAndPrint() {
        printer.printLine("Enter search query:");
        String searchedFraze = scanner.readLine();
        ContactBook searchedContacts = new ContactBook();
        //search
        SearchCommand searchCommand = new SearchCommand(contactBook, searchedContacts, searchedFraze);
        contactsController.setCommand(searchCommand);
        contactsController.executeCommand();
        //print result
        CountCommand countCommand = new CountCommand(searchedContacts);
        contactsController.setCommand(countCommand);
        contactsController.executeCommand();
        printer.printLine(String.format("Found %d results:", countCommand.getCounter()));
        if (countCommand.getCounter() > 0) {
            PrintListCommand printListCommand = new PrintListCommand(searchedContacts, printer);
            contactsController.setCommand(printListCommand);
            contactsController.executeCommand();
        }
        return searchedContacts;
    }

    private void addContact() {
        printer.printLine("Enter the type (person, organization):");
        String type = scanner.readLine();
        AddCommand addCommand = new AddCommand(contactBook, contactFactory, fileManager, saveToFile, ContactType.findByName(type));
        contactsController.setCommand(addCommand);
        contactsController.executeCommand();
        printer.printLine("The record added. \n");
    }


    private MainOptions getOptionFromUser() {
        MainOptions validatedOption = null;
        do {
            String optionFromUser = scanner.readLine();
            Optional<MainOptions> option = MainOptions.findOption(optionFromUser);
            if (option.isPresent()) {
                validatedOption = option.get();
            } else {
                printer.printLine("Bad parameters");
            }
        } while (validatedOption == null);
        return validatedOption;
    }

    private ContactBook initializeContactBookFile(String[] command) {
        if (command.length == 0) {
            return new ContactBook();

        } else {
            this.saveToFile = true;
            String fileName = command[0];
            File file = new File(fileName);
            this.fileManager = new SerializableFileManager(file);
            printer.printLine("open " + fileName);
            if (file.exists()) {
                return fileManager.readContacts();
            } else {
                return new ContactBook();
            }

        }

    }


}
