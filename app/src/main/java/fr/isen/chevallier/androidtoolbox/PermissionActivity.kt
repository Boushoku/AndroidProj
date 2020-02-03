package fr.isen.chevallier.androidtoolbox

import android.R.attr
import android.R.attr.bitmap
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_permission.*
import java.io.File
import java.security.acl.Permission
import java.util.jar.Manifest


class PermissionActivity : AppCompatActivity(), LocationListener {

    lateinit var locationManager: LocationManager

    companion object {
        val pictureRequestCode = 1
        val contactPermissionRequestCode = 2
        val locationPermissionCode = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        pictureButton.setOnClickListener {
            onChangePhoto()
        }

        requestPermission(android.Manifest.permission.READ_CONTACTS, contactPermissionRequestCode) {
            readContacts()
        }

        requestPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION, locationPermissionCode) {
            startGPS()
        }
    }

    fun onChangePhoto() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val intentChooser = Intent.createChooser(galleryIntent, "Choose your picture library")
        intentChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
        startActivityForResult(intentChooser, pictureRequestCode)
    }

    fun readContacts() {
        val contactList = ArrayList<ContactModel>()
        val contacts = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        while(contacts?.moveToNext() ?: false) {
            val displayName = contacts?.getString(contacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            val contactModel = ContactModel()
            contactModel.displayName = displayName.toString()
            contactList.add(contactModel)
        }
        contactRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        contactRecyclerView.adapter = ContactsAdapter(contactList)
    }

    @SuppressLint("MissingPermission")
    fun startGPS(){
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER,this,null)
        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        location.let { refreshPosition(it) }
    }

    fun refreshPosition(location: Location){
        Toast.makeText(this, "Latitude ${location.latitude} longitude : ${location.longitude}", Toast.LENGTH_LONG).show()
    }

    fun requestPermission(permissionToRequest: String, requestCode: Int, handler: ()-> Unit) {
        if(ContextCompat.checkSelfPermission(this, permissionToRequest) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, permissionToRequest)) {
                //display toast
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(permissionToRequest), requestCode)
            }
        } else {
            handler()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(grantResults.isNotEmpty()) {
            if (requestCode == contactPermissionRequestCode &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                readContacts()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == pictureRequestCode &&
            resultCode == Activity.RESULT_OK) {
            if(data?.data != null) { // Gallery
                pictureButton.setImageURI(data.data)
            } else { // Camera
                val bitmap = data?.extras?.get("data") as? Bitmap
                bitmap?.let {
                    pictureButton.setImageBitmap(it)
                }
            }
        }
    }
    override fun onLocationChanged(location: Location?) {
        location?.let {
            refreshPosition(it)
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String?) {}

    override fun onProviderDisabled(provider: String?) {}
}