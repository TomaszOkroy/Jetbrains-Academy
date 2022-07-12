package contacts.model.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements Validator{
    @Override
    public boolean validate(String lineToValidate) {
        Pattern firstPattern =
                Pattern.compile("((\\+?\\w+[\\s\\-]\\(\\w{2,}\\))|(\\+?\\(\\w+\\)[\\s\\-]\\w{2,})|(\\+?\\w+[\\s\\-]\\w{2,}))([\\s\\-]\\w{2,})*");
        Matcher firstMatcher = firstPattern.matcher(lineToValidate);
        Pattern secondPattern = Pattern.compile("\\+?(\\(?\\w+\\)?)");
        Matcher secondMatcher = secondPattern.matcher(lineToValidate);

        boolean firstResult = firstMatcher.matches();
        boolean secondResult = secondMatcher.matches();
        return firstResult || secondResult;

    }

    //\+?\(?\d{1,4}\)?\s|-\(?[\w]{2,4}\)?\s|-[\w]{2,4}\s|-[\w]{2,4}


    //((\+?(\w+[\s\-]\(\w{2,}\))|(\(\w+\)[\s\-]\w{2,}))([\s\-\w]*))
}
