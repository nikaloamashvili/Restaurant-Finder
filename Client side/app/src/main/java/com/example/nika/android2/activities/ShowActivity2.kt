package com.example.nika.android2.activities
import android.content.Intent

//import android.graphics.Color
import android.os.Bundle
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
import androidx.compose.ui.unit.dp
import com.example.nika.android2.CustomItem
import com.example.nika.android2.databinding.ActivityProfile2Binding
import com.example.nika.android2.models.Restaurant
import com.example.nika.android2.server.RetrieveFeedTask
import com.example.nika.android2.server.RetrieveFeedTaskForRestaurant
import com.example.nika.android2.ui.theme.Android2Theme
import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList

class showActivity2 : ComponentActivity() {
    private lateinit var binding: ActivityProfile2Binding
    private  lateinit var  firebaseAuth: FirebaseAuth
    private lateinit var actionBar: ActionBar
    private lateinit var sr:RetrieveFeedTask
    private lateinit var sr2:RetrieveFeedTaskForRestaurant
    var fav_clic_check : Boolean = false
    lateinit var getResturantData: ArrayList<Restaurant>
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android2Theme {
                firebaseAuth = FirebaseAuth.getInstance()
                val sections = listOf("Restaurants")
                getResturantData = RetrieveFeedTaskForRestaurant.getArrState()
                for (res in getResturantData) {
                    val firebaseUser = firebaseAuth.currentUser
                    if (firebaseUser != null) {
                        res.userEmail = firebaseUser.email.toString()
                    };
                }
                val myList = remember { mutableStateListOf<Restaurant>() }
                myList.swapList(getResturantData) // Returns a List<DailyItem> with latest values and uses mutable list internally
                Row() {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        sections.forEach { section ->
                            stickyHeader {
                                Row() {
                                }
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.LightGray)
                                        .padding(top = 24.dp),
                                    text = "Section $section"
                                )
                            }
                            itemsIndexed(items = myList) { index, resturants ->
                                CustomItem(restaurant = resturants, "-")
                            }
                        }
                    }
                }
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
    // but you can override this to do whatever you want.
    override fun onBackPressed() {
        selectRestaurant()
        getResturantData = RetrieveFeedTaskForRestaurant.getArrState()
        for (res in getResturantData) {
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser != null) {
                res.userEmail = firebaseUser.email.toString()
            };
        }
        super.onBackPressed()
    }
    private fun selectRestaurant() {
        val email11:String;
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            email11 = firebaseUser.email.toString()
        }else{
            email11 ="not ok"
        }
        val r =   RetrieveFeedTaskForRestaurant("3",email11)

        if (r != null) {
            r.execute()
        }
    }
}


