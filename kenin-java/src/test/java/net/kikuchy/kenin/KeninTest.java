package net.kikuchy.kenin;

import net.kikuchy.kenin.condition.TestCondition;
import net.kikuchy.kenin.result.ErrorReason;
import net.kikuchy.kenin.result.ResultReceiver;
import net.kikuchy.kenin.trigger.TestValueChangedEventRelay;

import org.junit.Before;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by hiroshi.kikuchi on 2016/02/03.
 */
public class KeninTest {
    TestValueChangedEventRelay<Boolean> emitter;
    TestCondition<Boolean> condition;

    @Before
    public void setUp() throws Exception {
        emitter = new TestValueChangedEventRelay<>();
        condition = new TestCondition<>();
    }

    @org.junit.Test
    public void onValueChangedCallsAllReceiver() throws Exception {
        Kenin<Boolean, String> one = Kenin.create(emitter, condition);
        one.addResultReceiver(new ResultReceiver<String>() {
            @Override
            public void validationSucceeded() {
                assertTrue(true);
            }

            @Override
            public void validationFailed(List<ErrorReason<String>> errorReasons) {
                assertTrue(false);
            }
        });
        one.addResultReceiver(new ResultReceiver<String>() {
            @Override
            public void validationSucceeded() {
                assertTrue(true);
            }

            @Override
            public void validationFailed(List<ErrorReason<String>> errorReasons) {
                assertTrue(false);
            }
        });
        one.addResultReceiver(new ResultReceiver<String>() {
            @Override
            public void validationSucceeded() {
                assertTrue(true);
            }

            @Override
            public void validationFailed(List<ErrorReason<String>> errorReasons) {
                assertTrue(false);
            }
        });
        condition.setValid(true);
        one.onValueChanged(true);
    }

    @org.junit.Test
    public void onValueChangedCalledViaRelay() throws Exception {
        Kenin<Boolean, String> one = Kenin.create(emitter, condition);
        one.addResultReceiver(new ResultReceiver<String>() {
            @Override
            public void validationSucceeded() {
                assertTrue(true);
            }

            @Override
            public void validationFailed(List list) {
                assertTrue(false);
            }
        });
        condition.setValid(true);
        emitter.emmit(true);

        Kenin<Boolean, String> two = Kenin.create(emitter, condition);
        two.addResultReceiver(new ResultReceiver<String>() {
            @Override
            public void validationSucceeded() {
                assertTrue(false);
            }

            @Override
            public void validationFailed(List<ErrorReason<String>> errorReasons) {
                assertTrue(true);
            }
        });
        condition.setValid(false);
        emitter.emmit(true);
    }
}