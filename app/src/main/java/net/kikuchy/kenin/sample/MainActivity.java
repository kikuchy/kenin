package net.kikuchy.kenin.sample;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import net.kikuchy.kenin.KeninAndroid;
import net.kikuchy.kenin.condition.CompositeCondition;
import net.kikuchy.kenin.condition.Conditions;
import net.kikuchy.kenin.internal.SameCondition;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout mUserId;
    private TextInputLayout mPassword;
    private TextInputLayout mPassConf;
    private TextInputLayout mAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserId = (TextInputLayout) findViewById(R.id.user_id);
        mPassword = (TextInputLayout) findViewById(R.id.password);
        mPassConf = (TextInputLayout) findViewById(R.id.password_confirm);
        mAmount = (TextInputLayout) findViewById(R.id.amount);

        KeninAndroid
                .builder(mUserId)
                .setCondition(
                        CompositeCondition.and(
                                Conditions.requireField("require!!!! must put some value!!"),
                                CompositeCondition.or(
                                        Conditions.alphabet(),
                                        Conditions.numeric()
                                )
                        )
                )
                .build();
        KeninAndroid.builder(mPassword)
                .setCondition(
                        CompositeCondition.and(
                                Conditions.alphanumeric("BE ALPHANUMERIC!!!!!!"),
                                Conditions.lengthMin(6, "Short password, risk your money.")))
                .build();
        KeninAndroid.builder(mPassConf)
                .setCondition(Conditions.same(new SameCondition.LazyGetter<CharSequence>() {
                    @Override
                    public CharSequence get() {
                        return mPassword.getEditText().getText().toString();
                    }
                }, "😞"))
                .build();
        KeninAndroid.builder(mAmount)
                .setCondition(Conditions.numeric("Read the hint above????"))
                .build();
    }
}
