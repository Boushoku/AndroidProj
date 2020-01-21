package fr.isen.chevallier.androidtoolbox

import android.content.Intent
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
        val goodId = "admin"
        val goodPw = "123"
        val buttonValidate = findViewById<Button>(R.id.validateButton)
        val identifiant = findViewById<EditText>(R.id.identifiant)
        val password = findViewById<EditText>(R.id.password)
        val intentHomePage = Intent(this, HomeActivity::class.java)
        buttonValidate.setOnClickListener{
            if (identifiant.text.toString() == goodId && password.text.toString() == goodPw){
                startActivity(intentHomePage)
                Toast.makeText(this, "Welcome " + identifiant.text.toString(), Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "Wrong username/password", Toast.LENGTH_LONG).show()
            }
        }

    }

}
