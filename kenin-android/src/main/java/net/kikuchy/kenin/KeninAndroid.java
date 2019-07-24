package net.kikuchy.kenin;

import com.google.android.material.textfield.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.CachedResultReceiver;
import net.kikuchy.kenin.result.ReasonStringifyResultReceiver;
import net.kikuchy.kenin.result.ResultReceiver;
import net.kikuchy.kenin.trigger.ValueChangedEventEmitter;
import net.kikuchy.kenin.trigger.ValueChangedEventRelay;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by hiroshi.kikuchi on 2016/02/01.
 */
public class KeninAndroid<V, E> extends Kenin<V, E> {
    protected KeninAndroid(ValueChangedEventRelay<V> relay, Condition<? super V, E> condition) {
        super(relay, condition);
    }


    @NotNull
    public static <E> Kenin<CharSequence, E> create(
            final EditText editText, final Condition<? super CharSequence, E> condition) {
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

    @NotNull
    public static <E> Kenin<CharSequence, E> create(
            final TextInputLayout textInputLayout,
            final Condition<? super CharSequence, E> condition,
            final ReasonStringifyResultReceiver.Stringifier<E> stringifier) {
        Kenin<CharSequence, E> kenin = create(textInputLayout.getEditText(), condition);
        kenin.addResultReceiver(
                new CachedResultReceiver<>(
                        new ReasonStringifyResultReceiver<>(stringifier,
                                new ResultReceiver<String>() {
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
                                })));
        return kenin;
    }

    @NotNull
    public static Kenin<CharSequence, String> create(
            final TextInputLayout textInputLayout,
            final Condition<? super CharSequence, String> condition) {
        return create(textInputLayout, condition,
                new ReasonStringifyResultReceiver.Stringifier<String>() {
                    @Override
                    public String stringify(String reason) {
                        return reason;
                    }
                });
    }

    @NotNull
    public static <E> Kenin<Boolean, E> create(
            final CheckBox checkBox, final Condition<? super Boolean, E> condition) {
        return new KeninAndroid<>(new ValueChangedEventRelay<Boolean>() {
            @Override
            public void relay(final ValueChangedEventEmitter<Boolean> emitter) {
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        emitter.emit(b);
                    }
                });
            }
        }, condition);
    }
}
