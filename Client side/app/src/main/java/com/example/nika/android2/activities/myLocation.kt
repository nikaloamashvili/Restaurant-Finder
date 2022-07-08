package com.example.nika.android2.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.nika.android2.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

private lateinit var fusedLocationClient: FusedLocationProviderClient


class myLocation : AppCompatActivity() {
    companion object{
        public var latitude by Delegates.notNull<Double>()
        public var longitude by Delegates.notNull<Double>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }


        // Create a new ThreadPoolExecutor with 2 threads for each processor on the
// device and a 60 second keep-alive time.
        // Create a new ThreadPoolExecutor with 2 threads for each processor on the
// device and a 60 second keep-alive time.
        val numCores = Runtime.getRuntime().availableProcessors()
        val executor = ThreadPoolExecutor(
            numCores * 2, numCores * 2,
            60L, TimeUnit.SECONDS, LinkedBlockingQueue<Runnable>()
        )

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                if (location != null) {
                     latitude = location.latitude
                     longitude = location.longitude
                }
                // Got last known location. In some rare situations this can be null.
            }.addOnCompleteListener {
                startActivity(Intent(this, showActivity::class.java))
                finish()
                // ...
            }.addOnFailureListener{
                latitude = -1.0
                longitude =-1.0
                startActivity(Intent(this, showActivity::class.java))
                finish()
            }

    }
}