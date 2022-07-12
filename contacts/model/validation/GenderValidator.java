package contacts.model.validation;

public class GenderValidator implements Validator{
    @Override
    public boolean validate(String lineToValidate) {
        return lineToValidate.isEmpty();
    }
}
