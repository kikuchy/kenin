package net.kikuchy.kenin;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import net.kikuchy.kenin.result.CachedResultReceiver;
import net.kikuchy.kenin.result.ErrorReason;
import net.kikuchy.kenin.result.ResultReceiver;
import net.kikuchy.kenin.trigger.ValueChangedEventRelay;

import java.util.List;

/**
 * Created by hiroshi.kikuchi on 2016/02/01.
 */
public class KeninAndroid<V, E> extends Kenin<V, E> {
    protected Context context;

    protected KeninAndroid(Builder<V, E> builder) {
        super(builder);
        this.context = builder.context;
    }

    public static <E> Kenin.Builder<CharSequence, E> builder(final EditText editText) {
        return new Builder<>(new ValueChangedEventRelay<CharSequence>() {
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
        }, editText.getContext());
    }

    public static Kenin.Builder<CharSequence, String> builder(final TextInputLayout textInputLayout) {
        return KeninAndroid.<String>builder(textInputLayout.getEditText())
                .addResultReceiver(new CachedResultReceiver<>(new ResultReceiver<String>() {
                    @Override
                    public void validationSucceeded() {
                        textInputLayout.setError(null);
                    }

                    @Override
                    public void validationFailed(List<ErrorReason<String>> errorMessages) {
                        StringBuilder builder = new StringBuilder();
                        for (ErrorReason<String> err : errorMessages) {
                            if (builder.length() != 0) {
                                builder.append('\n');
                            }
                            builder.append(err.getReason());
                        }
                        textInputLayout.setError(builder.toString());
                    }
                }));
    }

    public static class Builder<T, E> extends Kenin.Builder<T, E> {
        private Context context;

        protected Builder(ValueChangedEventRelay<T> relay, Context context) {
            super(relay);
            this.context = context;
        }

        public Builder<T, E> setContext(Context context) {
            this.context = context;
            return this;
        }
    }
}
