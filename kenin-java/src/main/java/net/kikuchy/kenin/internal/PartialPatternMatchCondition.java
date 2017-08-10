package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ValidationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hiroshi.kikuchi on 2016/02/12.
 */
public class PartialPatternMatchCondition<E> implements Condition<CharSequence, E> {
    private final E message;
    private Pattern pattern;

    public PartialPatternMatchCondition(Pattern pattern, final E errorReason) {
        this.pattern = pattern;
        this.message = errorReason;
    }

    public PartialPatternMatchCondition(String pattern, final E errorReason) {
        this(Pattern.compile(pattern), errorReason);
    }

    @Override
    public ValidationResult<E> validate(CharSequence value) {
        List<E> errors = new ArrayList<>();
        Matcher matcher = pattern.matcher(value);
        boolean isValid = matcher.find();
        if (!isValid)
            errors.add(message);
        return new ValidationResult<>(isValid, errors);
    }
}
