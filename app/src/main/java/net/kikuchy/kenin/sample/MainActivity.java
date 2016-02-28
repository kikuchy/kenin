package net.kikuchy.kenin.sample;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;

import net.kikuchy.kenin.KeninAndroid;
import net.kikuchy.kenin.condition.AndroidConditions;
import net.kikuchy.kenin.condition.Conditions;
import net.kikuchy.kenin.result.ResultReceiver;

import java.util.List;

import static net.kikuchy.kenin.condition.CompositeCondition.and;
import static net.kikuchy.kenin.condition.CompositeCondition.or;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout mUserId;
    private TextInputLayout mPassword;
    private TextInputLayout mPassConf;
    private TextInputLayout mAmount;
    private CheckBox mAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserId = (TextInputLayout) findViewById(R.id.user_id);
        mPassword = (TextInputLayout) findViewById(R.id.password);
        mPassConf = (TextInputLayout) findViewById(R.id.password_confirm);
        mAmount = (TextInputLayout) findViewById(R.id.amount);
        mAgreement = (CheckBox) findViewById(R.id.agreement);

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

        KeninAndroid.create(mAgreement, Conditions.requireChecked("You have to AGREEEEE"))
                .addResultReceiver(new ResultReceiver<String>() {
                    @Override
                    public void validationSucceeded() {
                        mAgreement.setError(null);
                    }

                    @Override
                    public void validationFailed(List<String> errorReasons) {
                        mAgreement.setError(errorReasons.get(0));
                    }
                });
    }
}
