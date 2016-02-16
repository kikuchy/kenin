package net.kikuchy.kenin;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.CachedResultReceiver;
import net.kikuchy.kenin.result.ResultReceiver;
import net.kikuchy.kenin.trigger.ValueChangedEventRelay;

import java.util.List;

/**
 * Created by hiroshi.kikuchi on 2016/02/01.
 */
public class KeninAndroid<V, E> extends Kenin<V, E> {
    protected KeninAndroid(ValueChangedEventRelay<V> relay, Condition<V, E> condition) {
        super(relay, condition);
    }


    public static <E> Kenin<CharSequence, E> create(
            final EditText editText, final Condition<CharSequence, E> condition) {
        return new KeninAndroid<>(new ValueChangedEventRelay<CharSequence>() {
            @Override
            public void relay(final net.kikuchy.kenin.trigger.ValueChangedEventEmitter<CharSequence> emitter) {
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        emitter.emit(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        }, condition);
    }

    public static Kenin<CharSequence, String> create(
            final TextInputLayout textInputLayout,
            final Condition<CharSequence, String> condition) {
        Kenin<CharSequence, String> kenin = create(textInputLayout.getEditText(), condition);
        kenin.addResultReceiver(new CachedResultReceiver<>(new ResultReceiver<String>() {
            @Override
            public void validationSucceeded() {
                textInputLayout.setError(null);
            }

            @Override
            public void validationFailed(List<String> errorMessages) {
                StringBuilder builder = new StringBuilder();
                for (String err : errorMessages) {
                    if (builder.length() != 0) {
                        builder.append('\n');
                    }
                    builder.append(err);
                }
                textInputLayout.setError(builder.toString());
            }
        }));
        return kenin;
    }
}
