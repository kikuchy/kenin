package net.kikuchy.kenin.kotlin

import net.kikuchy.kenin.kotlin.and
import net.kikuchy.kenin.kotlin.kenin
import net.kikuchy.kenin.trigger.ValueChangedEventEmitter
import net.kikuchy.kenin.trigger.ValueChangedEventRelay
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by kikuchy on 16/03/07.
 */
class KeninExtensionKtTest {

    @Test
    fun testKenin() {
        val relay = ValueChangedEventRelay<String> { emitter -> emitter?.emit("hoge") }
        relay.kenin { 
            sameText("moge") and lengthJust(4)
        }
    }
}