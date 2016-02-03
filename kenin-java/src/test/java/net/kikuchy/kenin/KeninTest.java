package net.kikuchy.kenin;

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
        Kenin<Boolean> one = Kenin
                .builder(emitter)
                .setCondition(condition)
                .addResultReceiver(new ResultReceiver() {
                    @Override
                    public void validationSucceeded() {
                        assertTrue(true);
                    }

                    @Override
                    public void validationFailed(List<String> errorMessages) {
                        assertTrue(false);
                    }
                })
                .addResultReceiver(new ResultReceiver() {
                    @Override
                    public void validationSucceeded() {
                        assertTrue(true);
                    }

                    @Override
                    public void validationFailed(List<String> errorMessages) {
                        assertTrue(false);
                    }
                })
                .addResultReceiver(new ResultReceiver() {
                    @Override
                    public void validationSucceeded() {
                        assertTrue(true);
                    }

                    @Override
                    public void validationFailed(List<String> errorMessages) {
                        assertTrue(false);
                    }
                }).build();
        condition.setValid(true);
        one.onValueChanged(true);
    }

    @org.junit.Test
    public void onValueChangedCalledViaRelay() throws Exception {
        Kenin<Boolean> one = Kenin.builder(emitter).setCondition(condition).addResultReceiver(new ResultReceiver() {
            @Override
            public void validationSucceeded() {
                assertTrue(true);
            }

            @Override
            public void validationFailed(List<String> errorMessages) {
                assertTrue(false);
            }
        }).build();
        condition.setValid(true);
        emitter.emmit(true);

        Kenin<Boolean> two = Kenin.builder(emitter).setCondition(condition).addResultReceiver(new ResultReceiver() {
            @Override
            public void validationSucceeded() {
                assertTrue(false);
            }

            @Override
            public void validationFailed(List<String> errorMessages) {
                assertTrue(true);
            }
        }).build();
        condition.setValid(false);
        emitter.emmit(true);
    }
}