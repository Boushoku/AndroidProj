package fr.isen.chevallier.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LifeCycleActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
    }
}