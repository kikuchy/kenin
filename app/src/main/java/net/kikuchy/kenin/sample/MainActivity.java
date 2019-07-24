package net.kikuchy.kenin.sample;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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

        mUserId = findViewById(R.id.user_id);
        mPassword = findViewById(R.id.password);
        mPassConf = findViewById(R.id.password_confirm);
        mAmount = findViewById(R.id.amount);
        mAgreement = findViewById(R.id.agreement);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add("Kotlin Version");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        startActivity(new Intent(this, KotlinMainActivity.class));
        return true;
    }
}
