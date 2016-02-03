package net.kikuchy.kenin.sample;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import net.kikuchy.kenin.KeninAndroid;
import net.kikuchy.kenin.internal.RequireCondition;
import net.kikuchy.kenin.internal.SameCondition;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout mUserId;
    private TextInputLayout mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserId = (TextInputLayout) findViewById(R.id.user_id);
        mPassword = (TextInputLayout) findViewById(R.id.password);

        KeninAndroid.
                builder(mUserId).
                setCondition(new RequireCondition()).
                build();
        KeninAndroid.builder(mPassword).
                setCondition(new SameCondition("password")).
                build();
    }
}
