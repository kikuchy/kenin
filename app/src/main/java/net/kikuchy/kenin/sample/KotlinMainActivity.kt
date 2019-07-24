package net.kikuchy.kenin.sample

import android.os.Bundle
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import net.kikuchy.kenin.kotlin.and
import net.kikuchy.kenin.kotlin.kenin
import net.kikuchy.kenin.kotlin.or
import net.kikuchy.kenin.result.ResultReceiver

class KotlinMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        val userId = findViewById<TextInputLayout>(R.id.user_id)
        val password = findViewById<TextInputLayout>(R.id.password)
        val passConf = findViewById<TextInputLayout>(R.id.password_confirm)
        val amount = findViewById<TextInputLayout>(R.id.amount)
        val agreement = findViewById<CheckBox>(R.id.agreement)

        userId.kenin {
            requireField("require!!!! must put some value!!") and (alphabet() or numeric())
        }

        password.kenin {
            alphanumeric("BE ALPHANUMERIC!!!!!!") and lengthMin(6, "Short password, risk your money.")
        }

        passConf.kenin { confirm(password, "😞") }

        amount.kenin { numeric("Read the hint above????") }

        agreement.
                kenin { requireChecked("You have to AGREEEEE") }.
                addResultReceiver(object : ResultReceiver<String> {
                    override fun validationSucceeded() {
                        agreement.error = null
                    }

                    override fun validationFailed(errorReasons: MutableList<String>?) {
                        agreement.error = errorReasons?.get(0)
                    }
                })
    }
}
