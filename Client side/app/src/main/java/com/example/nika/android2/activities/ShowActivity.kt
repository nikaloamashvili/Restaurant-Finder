package com.example.nika.android2.activities
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager

//import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.ActionBar
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.nika.android2.CustomItem
import com.example.nika.android2.R
import com.example.nika.android2.databinding.ActivityProfile2Binding
import com.example.nika.android2.lazycolum.Repository
import com.example.nika.android2.models.Restaurant
import com.example.nika.android2.server.RetrieveFeedTask
import com.example.nika.android2.server.RetrieveFeedTaskForRestaurant
import com.example.nika.android2.ui.theme.Android2Theme
import com.example.nika.android2.user.LoginActivity
import com.example.nika.android2.user.ProfileActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList

class showActivity : ComponentActivity() {

    private lateinit var binding: ActivityProfile2Binding
    private  lateinit var  firebaseAuth: FirebaseAuth
    private lateinit var actionBar: ActionBar
    private var locationManager : LocationManager? = null
    private lateinit var sr:RetrieveFeedTask
    private lateinit var sr2:RetrieveFeedTaskForRestaurant
    var fav_clic_check : Boolean = false

    var langt : Double = -1.0
    var longt : Double = -1.0

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Android2Theme  {

                firebaseAuth = FirebaseAuth.getInstance()
                sr=hhhh();
                sr2=hhhh2();


                locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
                try {
                    // Request location updates
                    locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
                } catch(ex: SecurityException) {
                    Log.d("myTag", "Security Exception, no location available")
                }
                val sections = listOf("Restaurants")
                // 32.109333
                // 34.855499 //long
                var getResturantData : ArrayList<Restaurant>
                if(langt==-1.0 || longt==-1.0 ){
                     getResturantData  = Repository.getArrState("-1","34.855499","32.109333")
                }else{
                     getResturantData  = Repository.getArrState("-1",longt.toString(),langt.toString())
                }

                for (res in getResturantData) {
                    val firebaseUser =firebaseAuth.currentUser
                    if (firebaseUser != null) {
                        res.userEmail= firebaseUser.email.toString()
                    };
                }
                val myList = remember { mutableStateListOf<Restaurant>() }
                myList.swapList(getResturantData) // Returns a List<DailyItem> with latest values and uses mutable list internally



                Row(){
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    sections.forEach { section ->
                        stickyHeader {
                            Row() {

                                var text by remember { mutableStateOf(TextFieldValue("")) }
                                TextField(
                                    modifier = Modifier.width(200.dp),
                                    value = text,
                                    onValueChange = { newText ->
                                        text = newText
                                    }
                                )
                                Button(onClick = {
                                    if(langt==-1.0 || longt==-1.0 ){
                                        getResturantData  = Repository.getArrState(text.text,"34.855499","32.109333")
                                    }else{
                                        getResturantData  = Repository.getArrState(text.text,longt.toString(),langt.toString())
                                    }
                                    for (res in getResturantData) {
                                        val firebaseUser =firebaseAuth.currentUser
                                        if (firebaseUser != null) {
                                            res.userEmail= firebaseUser.email.toString()
                                        };
                                    }

                                    //Toast.makeText(this@showActivity, "fs", Toast.LENGTH_SHORT).show()
                                        myList.swapList(getResturantData)
                                }) {
                                    //Text(text = "sherch")
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_find),
                                        contentDescription = null // decorative element
                                    )
                                }
                                Button(onClick = {
                                    sr2=hhhh2();
                                    showuser();

                                }) {
                                    //Text(text = "logOut")
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_user),
                                        contentDescription = null // decorative element
                                    )
                                }


                                Button(onClick = {
                                    firebaseAuth.signOut()
                                    checkUser()
                                }) {
                                    //Text(text = "logOut")
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_logout),
                                        contentDescription = null // decorative element
                                    )
                                }
                            }
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.LightGray)
                                    .padding(top = 24.dp)
                                    ,
                                text = "Section $section"
                            )

                        }


                            itemsIndexed(items = myList) { index, resturants ->
                                CustomItem(restaurant = resturants,"+")
                            }



                    }
                }}

            }
        }
    }
    fun <T> SnapshotStateList<T>.swapList(newList: List<T>){
        clear()
        addAll(newList)
    }

    private fun checkUser(){
        val firebaseUser =firebaseAuth.currentUser
        if (firebaseUser != null){
            val email =firebaseUser.email
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
    private fun showuser(){
        hhhh();

    startActivity(Intent(this, ProfileActivity::class.java))
            //finish()
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            langt=location.latitude
            longt=location.longitude
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }


    private fun hhhh(): RetrieveFeedTask {
        val email11:String;
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            email11 = firebaseUser.email.toString()
        }else{
            email11 ="not ok"
        }
        val r =   RetrieveFeedTask("1", "2", "3", "4", "5", email11,"0")
        if (r != null) {
            r.execute()
        }
        return r;
    }

    private fun hhhh2(): RetrieveFeedTaskForRestaurant {
        val email11:String;
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            email11 = firebaseUser.email.toString()
        }else{
            email11 ="not ok"
        }
        var res5 :Restaurant=Restaurant("3","3")
        val r =   RetrieveFeedTaskForRestaurant("3",res5,email11)

        if (r != null) {
            r.execute()
        }
        return r;
    }



}

