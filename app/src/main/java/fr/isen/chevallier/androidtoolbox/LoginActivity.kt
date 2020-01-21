package fr.isen.chevallier.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var goodId = "admin"
        var goodPw = "123"
        var buttonValidate = findViewById<Button>(R.id.validateButton)
        var identifiant = findViewById<EditText>(R.id.identifiant)
        var password = findViewById<EditText>(R.id.password)
        buttonValidate.setOnClickListener{
            if (identifiant.text.toString() == goodId && password.text.toString() == goodPw){
                Toast.makeText(this, "Welcome " + identifiant.text.toString(), Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "Wrong username/password", Toast.LENGTH_LONG).show()
            }
        }

    }

}
