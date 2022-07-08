package com.example.nika.android2.activities
import android.content.Intent
//import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.nika.android2.repository.Repository
import com.example.nika.android2.models.Restaurant
import com.example.nika.android2.server.RetrieveFeedTask
import com.example.nika.android2.server.RetrieveFeedTaskForRestaurant
import com.example.nika.android2.ui.theme.Android2Theme
import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList


class showActivity : ComponentActivity() {
    private lateinit var binding: ActivityProfile2Binding
    private  lateinit var  firebaseAuth: FirebaseAuth
    lateinit var getResturantData : ArrayList<Restaurant>
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            Android2Theme  {
                lazy()
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
        selectUser();

    startActivity(Intent(this, ProfileActivity::class.java))
    }

    private fun addEmail(restaurants:ArrayList<Restaurant>){
        for (res in restaurants) {
            val firebaseUser =firebaseAuth.currentUser
            if (firebaseUser != null) {
                res.userEmail= firebaseUser.email.toString()
            };
        }
    }
    @ExperimentalFoundationApi
    @Composable
    private fun lazy() {
        firebaseAuth = FirebaseAuth.getInstance()
        selectUser();
        selectRestaurant();
        val sections = listOf("Restaurants")
        getResturantData  = Repository.getArrState("-1",myLocation.longitude.toString(),myLocation.latitude.toString())
        addEmail(getResturantData)
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
                                modifier = Modifier.width(230.dp),
                                value = text,
                                onValueChange = { newText ->
                                    text = newText
                                }
                            )
                            Button(onClick = {
                                getResturantData  = Repository.getArrState(text.text,myLocation.longitude.toString(),myLocation.latitude.toString())
                                addEmail(getResturantData)
                                myList.swapList(getResturantData)
                            }) {
                                //Text(text = "sherch")
                                Icon(
                                    modifier= Modifier.height(40.dp),
                                    painter = painterResource(id = R.drawable.ic_find),
                                    contentDescription = null // decorative element
                                )
                            }
                            Button(onClick = {
                                selectRestaurant();
                                showuser();
                            }) {
                                //Text(text = "logOut")
                                Icon(
                                    modifier= Modifier.height(40.dp),
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
                                    modifier= Modifier.height(40.dp),
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


    private fun selectUser() {
        val email11:String;
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            email11 = firebaseUser.email.toString()
        }else{
            email11 ="not ok"
        }
        val r =   RetrieveFeedTask("1",  email11)
        if (r != null) {
            r.execute()
        }
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



