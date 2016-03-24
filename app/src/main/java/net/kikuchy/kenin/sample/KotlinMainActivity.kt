package net.kikuchy.kenin.sample

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.widget.CheckBox
import net.kikuchy.kenin.kotlin.and
import net.kikuchy.kenin.kotlin.kenin
import net.kikuchy.kenin.kotlin.or
import net.kikuchy.kenin.result.ResultReceiver

class KotlinMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        val userId = findViewById(R.id.user_id) as TextInputLayout
        val password = findViewById(R.id.password) as TextInputLayout
        val passConf = findViewById(R.id.password_confirm) as TextInputLayout
        val amount = findViewById(R.id.amount) as TextInputLayout
        val agreement = findViewById(R.id.agreement) as CheckBox

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
                        agreement.setError(null)
                    }

                    override fun validationFailed(errorReasons: MutableList<String>?) {
                        agreement.setError(errorReasons?.get(0))
                    }
                })
    }
}
