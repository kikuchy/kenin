package net.kikuchy.kenin.condition;

import com.google.android.material.textfield.TextInputLayout;
import android.widget.EditText;

import net.kikuchy.kenin.internal.SameCondition;

/**
 * Utility methods for Android widgets.
 */
public final class AndroidConditions {
    public static Condition<CharSequence, String> confirm(final EditText editText, String errorMessage) {
        return Conditions.sameText(new SameCondition.LazyGetter<CharSequence>() {
            @Override
            public CharSequence get() {
                return editText.getText();
            }
        }, errorMessage);
    }

    public static Condition<CharSequence, String> confirm(TextInputLayout textInput, String errorMessage) {
        return confirm(textInput.getEditText(), errorMessage);
    }
}
