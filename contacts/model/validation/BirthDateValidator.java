package contacts.model.validation;

public class BirthDateValidator implements Validator{
    @Override
    public boolean validate(String lineToValidate) {
        return lineToValidate.isEmpty();
    }
}
