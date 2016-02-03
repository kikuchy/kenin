package net.kikuchy.kenin;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import net.kikuchy.kenin.internal.RequireCondition;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserId = (TextInputLayout) findViewById(R.id.user_id);

        KeninAndroid.
                builder(mUserId).
                setCondition(new RequireCondition()).
                build();
    }
}
