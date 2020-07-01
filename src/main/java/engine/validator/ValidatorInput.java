package engine.validator;

import org.apache.commons.lang3.math.NumberUtils;

import javax.enterprise.context.RequestScoped;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequestScoped
public class ValidatorInput {

    public boolean validateSpecialChars(String input) {
        Pattern compiledPattern = Pattern.compile("\\p{Lu}+[\\p{L}-]{1,40}");
        Matcher matcher = compiledPattern.matcher(input);
        return matcher.matches();
    }

    public boolean validateMobilePhone(String input) {
        Pattern compiledPattern = Pattern.compile("^\\d{9}$");
        Matcher matcher = compiledPattern.matcher(input);
        return matcher.matches();
    }

    public boolean validateEmail(String input) {
        Pattern compiledPattern =
                Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        Matcher matcher = compiledPattern.matcher(input);
        return matcher.matches();
    }
}

