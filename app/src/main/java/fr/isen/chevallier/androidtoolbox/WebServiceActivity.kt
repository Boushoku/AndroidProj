package fr.isen.chevallier.androidtoolbox

import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_permission.*

class WebServiceActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webservices)

        val textView = findViewById<TextView>(R.id.afficheTest)
// ...

// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://randomuser.me/api/?results=20"

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                textView.text = "Response is: ${response.substring(0, 500)}"
            },
            Response.ErrorListener { textView.text = "That didn't work!" })

// Add the request to the RequestQueue.
        queue.add(stringRequest)

    }
}