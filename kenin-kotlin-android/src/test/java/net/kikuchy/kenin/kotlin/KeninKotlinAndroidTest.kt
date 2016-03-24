package net.kikuchy.kenin.kotlin

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.widget.EditText
import net.kikuchy.kenin.kotlin.and
import net.kikuchy.kenin.kotlin.kenin
import net.kikuchy.kenin.result.ResultReceiver
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KeninKotlinAndroidTest {
    internal fun getContext(): Context {
        return InstrumentationRegistry.getTargetContext()
    }

    @Test
    @Throws(Exception::class)
    fun testEditText() {
        val et = EditText(getContext())
        et.setText("a")
        et.
                kenin { sameText("ab") and lengthJust(2) }.
                addResultReceiver(object : ResultReceiver<String> {
                    override fun validationSucceeded() {
                        assertTrue(true)
                    }

                    override fun validationFailed(errorReasons: MutableList<String>?) {
                        assertTrue(false)
                    }
                })
        et.setText("ab")
    }

    @Test
    fun testConfirm() {
        val expect = EditText(getContext())
        val confirm = EditText(getContext())
        confirm.
                kenin { confirm(expect, "must be same with another text") }.
                addResultReceiver(object : ResultReceiver<String> {
                    override fun validationSucceeded() {
                        assertTrue(true)
                    }

                    override fun validationFailed(errorReasons: MutableList<String>?) {
                        assertTrue(false)
                    }
                })
        expect.setText("foobar")
        confirm.setText("foobar")
    }
}