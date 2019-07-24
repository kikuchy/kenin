package net.kikuchy.kenin;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.test.platform.app.InstrumentationRegistry;

import net.kikuchy.kenin.condition.Conditions;
import net.kikuchy.kenin.result.ResultReceiver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class KeninAndroidTest {
    Context getContext() {
        return InstrumentationRegistry.getInstrumentation().getContext();
    }

    @Test
    public void editTextValidationFailed() throws Exception {
        EditText et = new EditText(getContext());
        et.setText("a");
        KeninAndroid.create(et, Conditions.requireField()).addResultReceiver(new ResultReceiver<String>() {
            @Override
            public void validationSucceeded() {
                assertTrue(false);
            }

            @Override
            public void validationFailed(List<String> list) {
                assertTrue(true);
            }
        });
        et.setText("");
    }

    @Test
    public void editTextValidationSucceeded() throws Exception {
        EditText et = new EditText(getContext());
        et.setText("");
        KeninAndroid.create(et, Conditions.requireField()).addResultReceiver(new ResultReceiver<String>() {
            @Override
            public void validationSucceeded() {
                assertTrue(true);
            }

            @Override
            public void validationFailed(List<String> list) {
                assertTrue(false);
            }
        });
        et.setText("a");
    }

    @Test
    public void checkBox() throws Exception {
        CheckBox cb = new CheckBox(getContext());
        KeninAndroid.create(cb, Conditions.requireChecked())
                .addResultReceiver(new ResultReceiver<String>() {
                    @Override
                    public void validationSucceeded() {
                        assertTrue(true);
                    }

                    @Override
                    public void validationFailed(List<String> list) {
                        assertTrue(false);
                    }
                });
        cb.setChecked(true);
    }
}