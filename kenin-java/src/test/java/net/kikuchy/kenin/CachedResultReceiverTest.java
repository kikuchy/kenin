package net.kikuchy.kenin;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by hiroshi.kikuchi on 2016/02/04.
 */
public class CachedResultReceiverTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testValidationSucceeded() throws Exception {
        CounterReceiver counter = new CounterReceiver();
        ResultReceiver cached = new CachedResultReceiver(counter);
        // call fourth
        cached.validationSucceeded();
        cached.validationSucceeded();
        cached.validationSucceeded();
        cached.validationSucceeded();

        // but called "succeeded" once
        assertThat(counter.countSucceed, is(1));
    }

    @Test
    public void testValidationFailed() throws Exception {
        CounterReceiver counter = new CounterReceiver();
        ResultReceiver cached = new CachedResultReceiver(counter);

        // call twice
        cached.validationFailed(Arrays.asList("hoge", "fuga"));
        cached.validationFailed(Arrays.asList("hoge", "fuga"));

        //but called "failed" once
        assertThat(counter.countFail, is(1));

        // call fail with other messages
        cached.validationFailed(Arrays.asList("hoge", "moge"));

        // and counter is counted up
        assertThat(counter.countFail, is(2));
    }

    class CounterReceiver implements ResultReceiver {
        public int countSucceed = 0;
        public int countFail = 0;

        @Override
        public void validationSucceeded() {
            countSucceed++;
        }

        @Override
        public void validationFailed(List<String> errorMessages) {
            countFail++;
        }
    }
}