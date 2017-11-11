package net.kikuchy.kenin.kotlin

import net.kikuchy.kenin.Kenin
import net.kikuchy.kenin.condition.CompositeCondition
import net.kikuchy.kenin.condition.Condition
import net.kikuchy.kenin.condition.Conditions
import net.kikuchy.kenin.trigger.ValueChangedEventRelay

/**
 * Created by kikuchy on 16/02/29.
 */
fun <V, E> ValueChangedEventRelay<V>.kenin(condition: Condition<in V, E>) = Kenin.create(this, condition)

fun <V, E> ValueChangedEventRelay<V>.kenin(init: KeninBuilder.() -> Condition<in V, E>): Kenin<V, E> {
    val builder = KeninBuilder()
    return Kenin.create(this, builder.init())
}

infix fun <V, E> Condition<V, E>.and(a: Condition<V, E>) = CompositeCondition.and(this, a)
infix fun <V, E> Condition<V, E>.or(a: Condition<V, E>) = CompositeCondition.or(this, a)

open class KeninBuilder {
    fun requireField() = Conditions.requireField()
    fun requireField(reason: String) = Conditions.requireField(reason)
    fun requireChecked() = Conditions.requireChecked()
    fun requireChecked(reason: String) = Conditions.requireChecked(reason)
    fun sameText(expected: CharSequence) = Conditions.sameText(expected)
    fun sameText(expected: CharSequence, reason: String) = Conditions.sameText(expected, reason)
    fun <V : CharSequence> sameText(expected: () -> V, reason: String)
            = Conditions.sameText(expected, reason)

    fun sameBool(expected: () -> Boolean, reason: String)
            = Conditions.sameBool(expected, reason)

    fun sameInt(expected: () -> Int, reason: String) = Conditions.sameInt(expected, reason)
    fun lengthJust(length: Int) = Conditions.lengthJust(length)
    fun lengthJust(length: Int, reason: String) = Conditions.lengthJust(length, reason)
    fun lengthMin(length: Int) = Conditions.lengthMin(length)
    fun lengthMin(length: Int, reason: String) = Conditions.lengthMin(length, reason)
    fun lengthMax(length: Int) = Conditions.lengthMax(length)
    fun lengthMax(length: Int, reason: String) = Conditions.lengthMax(length, reason)
    fun partialPattern(pattern: String) = Conditions.partialPattern(pattern)
    fun partialPattern(pattern: String, reason: String) = Conditions.partialPattern(pattern, reason)
    fun fullPattern(pattern: String) = Conditions.fullPattern(pattern)
    fun fullPattern(pattern: String, reason: String) = Conditions.fullPattern(pattern, reason)
    fun numeric() = Conditions.numeric()
    fun numeric(reason: String) = Conditions.numeric(reason)
    fun alphabet() = Conditions.alphabet()
    fun alphabet(reason: String) = Conditions.alphabet(reason)
    fun alphanumeric() = Conditions.alphanumeric()
    fun alphanumeric(reason: String) = Conditions.alphanumeric(reason)
}