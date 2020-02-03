package fr.isen.chevallier.androidtoolbox

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class HomeActivity : AppCompatActivity(){
    private val sharedPreferencesFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val gotosaveButton = findViewById<ImageButton>(R.id.gotosaveButton)
        gotosaveButton.setOnClickListener { startActivity(Intent(this, SaveActivity::class.java)) }
        val permissionButton = findViewById<ImageButton>(R.id.permissionButton)
        permissionButton.setOnClickListener { startActivity(Intent(this, PermissionActivity::class.java)) }
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPreferencesFile,
            Context.MODE_PRIVATE)
        val buttonLifeCycle = findViewById<ImageButton>(R.id.buttonLifeCycle)
        val intentLifeCycle = Intent(this, LifeCycleActivity::class.java)
        buttonLifeCycle.setOnClickListener{
            startActivity(intentLifeCycle)
        }
        val buttonDeconnexion = findViewById<Button>(R.id.decoButton)
        buttonDeconnexion.setOnClickListener(){
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
        }
    }
}