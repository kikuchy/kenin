package net.kikuchy.kenin.kotlin

import android.support.design.widget.TextInputLayout
import android.widget.CheckBox
import android.widget.EditText
import net.kikuchy.kenin.KeninAndroid
import net.kikuchy.kenin.kotlin.KeninBuilder
import net.kikuchy.kenin.condition.AndroidConditions
import net.kikuchy.kenin.condition.Condition
import net.kikuchy.kenin.result.ReasonStringifyResultReceiver

/**
 * Created by hiroshi.kikuchi on 2016/03/22.
 */
fun <E> EditText.kenin(condition: Condition<in CharSequence, E>) = KeninAndroid.create(this, condition)

fun <E> EditText.kenin(init: KeninAndroidBuilder.() -> Condition<in CharSequence, E>) =
        KeninAndroid.create(this, KeninAndroidBuilder().init())

fun <E> TextInputLayout.kenin(
        condition: Condition<in CharSequence, E>,
        stringifier: ReasonStringifyResultReceiver.Stringifier<E>) =
        KeninAndroid.create(this, condition, stringifier)

fun <E> TextInputLayout.kenin(
        stringifier: ReasonStringifyResultReceiver.Stringifier<E>,
        init: KeninAndroidBuilder.() -> Condition<in CharSequence, E>) =
        KeninAndroid.create(this, KeninAndroidBuilder().init(), stringifier)

fun TextInputLayout.kenin(condition: Condition<in CharSequence, String>) =
        KeninAndroid.create(this, condition)

fun TextInputLayout.kenin(init: KeninAndroidBuilder.() -> Condition<in CharSequence, String>) =
        KeninAndroid.create(this, KeninAndroidBuilder().init())

fun <E> CheckBox.kenin(condition: Condition<in Boolean, E>) =
        KeninAndroid.create(this, condition)

fun <E> CheckBox.kenin(init: KeninAndroidBuilder.() -> Condition<in Boolean, E>) =
        KeninAndroid.create(this, KeninAndroidBuilder().init())

class KeninAndroidBuilder : KeninBuilder() {
    fun confirm(editText: EditText, errorReason: String) =
            AndroidConditions.confirm(editText, errorReason)
    fun confirm(textInputLayout: TextInputLayout, errorReason: String) =
            AndroidConditions.confirm(textInputLayout, errorReason)
}