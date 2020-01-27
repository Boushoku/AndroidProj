package fr.isen.chevallier.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class LifeCycleActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
    }

    override fun onStart() {
        super.onStart()
        Log.i("TAG", "onStart called")
        //or a toast
    }
    override fun onResume() {
        super.onResume()
        Log.i("TAG", "onResume called")
    }
    override fun onPause() {
        super.onPause()
        Log.i("TAG", "onPause called")
    }
    override fun onStop() {
        super.onStop()
        Log.i("TAG", "onStop called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("TAG", "onRestart called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TAG", "onDestroy called")
    }
}