package fr.isen.chevallier.androidtoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class HomeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val buttonLifeCycle = findViewById<ImageButton>(R.id.buttonLifeCycle)
        val intentLifeCycle = Intent(this, LifeCycleActivity::class.java)
        buttonLifeCycle.setOnClickListener{
            startActivity(intentLifeCycle)
        }
    }
}