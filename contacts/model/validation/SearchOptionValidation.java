package contacts.model.validation;

import contacts.model.ContactBook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchOptionValidation implements Validator{
    @Override
    public boolean validate(String lineToValidate) {
        return false;
    }

    public static boolean isNumberInrRange(ContactBook contactBook, String potentialNumber) {
        Pattern pattern = Pattern.compile("[1-" + contactBook.getContacts().size() + "]");
        Matcher matcher = pattern.matcher(potentialNumber);
        return matcher.matches();
    }
}
