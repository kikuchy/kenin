package net.kikuchy.kenin

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
        val relay = object : ValueChangedEventRelay<String> {
            override fun relay(emitter: ValueChangedEventEmitter<String>?) {
                emitter?.emit("hoge")
            }
        }
        relay.kenin { 
            sameText("moge") and lengthJust(4)
        }
    }
}