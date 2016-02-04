package net.kikuchy.kenin.condition;

import net.kikuchy.kenin.internal.AlwaysValidCondition;
import net.kikuchy.kenin.result.ErrorReason;
import net.kikuchy.kenin.result.ValidationResult;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by hiroshi.kikuchi on 2016/02/12.
 */
public class CompositeConditionTest {
    TestCondition<String> testOne, testTwo, testThree;

    @Before
    public void setUp() throws Exception {
        testOne = new TestCondition<>();
        testTwo = new TestCondition<>();
        testThree = new TestCondition<>();
    }

    @Test
    public void testAndValid() throws Exception {
        Condition<String, String> one = CompositeCondition.and(testOne, testTwo);
        testOne.setValid(true);
        testTwo.setValid(true);
        ValidationResult<String> result = one.validate("hoge");
        assertTrue(result.isValid());
    }

    @Test
    public void testAndFail() throws Exception {
        Condition<String, String> one = CompositeCondition.and(testOne, testTwo);
        testOne.setValid(true);
        testTwo.setValid(false);
        ValidationResult<String> resultOne = one.validate("hoge");
        assertFalse(resultOne.isValid());

        testOne.setValid(false);
        testTwo.setValid(true);
        ValidationResult<String> resultTwo = one.validate("hoge");
        assertFalse(resultTwo.isValid());

        testOne.setValid(false);
        testTwo.setValid(false);
        ValidationResult<String> resultThree = one.validate("hoge");
        assertFalse(resultThree.isValid());
    }

    @Test
    public void testAndLeftAssociativity() throws Exception {
        Condition<String, Integer> condition = CompositeCondition.and(new IdentifiedCondition(1),
                new IdentifiedCondition(2), new IdentifiedCondition(3));
        ValidationResult<Integer> one = condition.validate("hoge");
        assertThat(one.getReasons().get(0).getReason(), is(1));
        assertThat(one.getReasons().get(1).getReason(), is(2));
        assertThat(one.getReasons().get(2).getReason(), is(3));
    }

    @Test
    public void testOrValid() throws Exception {
        Condition<String, String> condition = CompositeCondition.or(testOne, testTwo);
        testOne.setValid(true);
        testTwo.setValid(true);
        ValidationResult<String> one = condition.validate("hoge");
        assertTrue(one.isValid());

        testOne.setValid(true);
        testTwo.setValid(false);
        ValidationResult<String> two = condition.validate("hoge");
        assertTrue(two.isValid());

        testOne.setValid(false);
        testTwo.setValid(true);
        ValidationResult<String> three = condition.validate("hoge");
        assertTrue(three.isValid());
    }

    @Test
    public void testOrFail() throws Exception {
        Condition<String, String> condition = CompositeCondition.or(testOne, testTwo);
        testOne.setValid(false);
        testTwo.setValid(false);
        ValidationResult<String> one = condition.validate("hoge");
        assertFalse(one.isValid());
    }

    @Test
    public void testOrLeftAssociativity() throws Exception {
        Condition<String, Integer> conditionOne = CompositeCondition.or(new IdentifiedCondition(1),
                new AlwaysValidCondition<String, Integer>(), new IdentifiedCondition(3));
        ValidationResult<Integer> one = conditionOne.validate("hoge");
        assertThat(one.getReasons().size(), is(1));
        assertThat(one.getReasons().get(0).getReason(), is(1));

        Condition<String, Integer> conditionTwo = CompositeCondition.or(
                new AlwaysValidCondition<String, Integer>(),
                new IdentifiedCondition(2), new IdentifiedCondition(3));
        ValidationResult<Integer> two = conditionTwo.validate("hoge");
        assertThat(two.getReasons().size(), is(0));

        Condition<String, Integer> conditionThree = CompositeCondition.or(new IdentifiedCondition(1),
                new IdentifiedCondition(2), new IdentifiedCondition(3));
        ValidationResult<Integer> three = conditionThree.validate("hoge");
        assertThat(three.getReasons().size(), is(3));
        assertThat(three.getReasons().get(0).getReason(), is(1));
        assertThat(three.getReasons().get(1).getReason(), is(2));
        assertThat(three.getReasons().get(2).getReason(), is(3));
    }

    static class IdentifiedCondition implements Condition<String, Integer> {
        int id = 0;

        public IdentifiedCondition(int id) {
            this.id = id;
        }

        @Override
        public ValidationResult<Integer> validate(String value) {
            ErrorReason<Integer> result = new ErrorReason<Integer>() {
                @Override
                public Integer getReason() {
                    return id;
                }
            };
            return new ValidationResult<>(false, Arrays.asList(result));
        }
    }
}