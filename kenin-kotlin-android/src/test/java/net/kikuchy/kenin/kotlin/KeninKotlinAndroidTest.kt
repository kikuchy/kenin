package net.kikuchy.kenin.kotlin

import android.content.Context
import android.widget.EditText
import androidx.test.platform.app.InstrumentationRegistry
import net.kikuchy.kenin.result.ResultReceiver
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class KeninKotlinAndroidTest {
    internal fun getContext(): Context {
        return InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    @Throws(Exception::class)
    fun testEditText() {
        val et = EditText(getContext())
        et.setText("a")
        et.kenin { sameText("ab") and lengthJust(2) }.addResultReceiver(object : ResultReceiver<String> {
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
        confirm.kenin { confirm(expect, "must be same with another text") }.addResultReceiver(object : ResultReceiver<String> {
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