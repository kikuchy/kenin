package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ValidationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullPatternMatchCondition<E> implements Condition<CharSequence, E> {
    private final E message;
    private Pattern pattern;

    public FullPatternMatchCondition(Pattern pattern, E errorReason) {
        this.message = errorReason;
        this.pattern = pattern;
    }

    public FullPatternMatchCondition(String pattern, E errorReason) {
        this(Pattern.compile(pattern), errorReason);
    }

    @Override
    public ValidationResult<E> validate(CharSequence value) {
        List<E> errors = new ArrayList<>();
        Matcher matcher = pattern.matcher(value);
        boolean isValid = matcher.matches();
        if (!isValid)
            errors.add(message);
        return new ValidationResult<>(isValid, errors);
    }
}
