package fr.isen.chevallier.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class LifeCycleActivity : AppCompatActivity(), OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        val firstFragment = FirstFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, firstFragment).commit()
    }

    override fun swipeFragment() {
        val secondFragment = SecondFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, secondFragment).commit()
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