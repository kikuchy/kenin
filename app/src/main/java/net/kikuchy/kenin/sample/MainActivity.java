package net.kikuchy.kenin.sample;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import net.kikuchy.kenin.KeninAndroid;
import net.kikuchy.kenin.condition.AndroidConditions;
import net.kikuchy.kenin.condition.Conditions;

import static net.kikuchy.kenin.condition.CompositeCondition.and;
import static net.kikuchy.kenin.condition.CompositeCondition.or;

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

        KeninAndroid.create(
                mUserId,
                and(
                        Conditions.requireField("require!!!! must put some value!!"),
                        or(Conditions.alphabet(), Conditions.numeric())
                ));

        KeninAndroid.create(
                mPassword,
                and(
                        Conditions.alphanumeric("BE ALPHANUMERIC!!!!!!"),
                        Conditions.lengthMin(6, "Short password, risk your money.")));

        KeninAndroid.create(mPassConf, AndroidConditions.confirm(mPassword, "😞"));

        KeninAndroid.create(mAmount, Conditions.numeric("Read the hint above????"));
    }
}
