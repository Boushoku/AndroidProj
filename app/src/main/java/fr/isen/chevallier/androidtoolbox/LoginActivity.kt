package fr.isen.chevallier.androidtoolbox

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private val sharedPreferencesFile = "kotlinsharedpreference"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPreferencesFile,
            Context.MODE_PRIVATE)
        val sharedIdValue = sharedPreferences.getString("id_key","")
        val sharedPwValue = sharedPreferences.getString("name_key","")
        val goodId = "admin"
        val goodPw = "123"
        val buttonValidate = findViewById<Button>(R.id.validateButton)
        val identifiant = findViewById<EditText>(R.id.identifiant)
        val password = findViewById<EditText>(R.id.password)
        val intentHomePage = Intent(this, HomeActivity::class.java)
        Toast.makeText(this, "id sauvegardé " + sharedIdValue, Toast.LENGTH_LONG).show()
        if (sharedIdValue?.length != 0){
            identifiant.setText(sharedIdValue)
            password.setText(sharedPwValue)
        }
        buttonValidate.setOnClickListener{
            if (identifiant.text.toString() == goodId && password.text.toString() == goodPw){

                val editor: SharedPreferences.Editor = sharedPreferences.edit()

                editor.putString("id_key",goodId)
                editor.putString("password_key",goodPw)
                editor.apply()
                editor.commit()


                Toast.makeText(this, "Saved:Email" + sharedIdValue.toString() +"\nSaved:Password" + sharedPwValue.toString(), Toast.LENGTH_LONG).show()



                startActivity(intentHomePage)
                Toast.makeText(this, "Welcome " + identifiant.text.toString(), Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "Wrong username/password", Toast.LENGTH_LONG).show()
            }
        }

    }

}
