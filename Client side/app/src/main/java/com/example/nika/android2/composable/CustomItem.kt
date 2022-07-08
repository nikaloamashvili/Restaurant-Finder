package com.example.nika.android2

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.nika.android2.models.Restaurant
import com.example.nika.android2.server.RetrieveFeedTask
import com.example.nika.android2.server.RetrieveFeedTaskForRestaurant
import com.example.nika.android2.ui.theme.Typography
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startActivity











@Composable
fun CoilImage(it: String) {
    Box(modifier= Modifier
        .height(150.dp)
        .width(150.dp),
        contentAlignment = Alignment.Center)
    {
        val painter1= rememberImagePainter(
            data =it,
            builder ={
                error(R.drawable.erorr_1)
            }
        )
        val printerState =painter1.state
        Image(painter =painter1,contentDescription="logo")
        if(printerState is ImagePainter.State.Loading){
            CircularProgressIndicator()
        }
    }
}


@Composable
fun CustomItem(restaurant:Restaurant,typeofc:String) {
    var click by remember { mutableStateOf(false) }


    val context1 = LocalContext.current
    Row(
        modifier = Modifier
            .clickable {

            }
            .background(Color.LightGray)
            .fillMaxWidth()
            .border(12.dp, Color.Blue)
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column() {
            if(!click){
                CoilImage(restaurant.photo)
            }


        }
        Column() {
            Row() {
                if(click){
                    Text(
                        text = "removed",
                        color = Color.Black,
                        fontSize = Typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }else{
                    Text(
                        text = "${restaurant.name.substring(0,Math.min(10,restaurant.name.length))}",
                        color = Color.Black,
                        fontSize = Typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
            Row() {
                if(click){
                    Text(
                        text = "removed",
                        color = Color.Black,
                        fontSize = Typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Bold
                    )}
                else{
                    Text(
                    text = restaurant.raw_ranking.substring(0,4),
                    color = Color.Black,
                    fontSize = Typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal
                )  }
            }
            Row()
            {
                if(click){
                    Text(
                        text = "removed",
                        color = Color.Black,
                        fontSize = Typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Bold
                    )}else{


                Text(
                    text = restaurant.address,
                    color = Color.Black,
                    fontSize = Typography.h5.fontSize,
                    fontWeight = FontWeight.Normal
                ) }
            }
        }

        Spacer(Modifier.weight(1f))
        Column() {
            if(click==true) {

            }else{
            Button(onClick = {

                if(typeofc.equals("+")) {
                    Log.d("1after1", "1")

                    var r = RetrieveFeedTaskForRestaurant("2", restaurant, "3@gmail.com")
                    r.execute()
                    Toast.makeText(context1, "added to favorites", Toast.LENGTH_SHORT).show()
                }else{
                    Log.d("2after2", "2")

                    var r = RetrieveFeedTaskForRestaurant("4", restaurant, "3@gmail.com")
                    r.execute()
                    Toast.makeText(context1, "removed from favorites", Toast.LENGTH_SHORT).show()
                    click=true;
                }

            }) {
                //Text(text = "logOut")
                    if(typeofc.equals("+")){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = null // decorative element
                        )
                    }else{
                        Icon(
                            painter = painterResource(id = R.drawable.ic_remove),
                            contentDescription = null // decorative element
                        )
                    }


            }


        }}
    }

}









