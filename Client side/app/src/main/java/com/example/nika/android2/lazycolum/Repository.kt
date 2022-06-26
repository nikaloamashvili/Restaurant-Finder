package com.example.nika.android2.lazycolum

import android.os.StrictMode
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

import org.json.JSONObject
import org.json.JSONTokener
import android.util.Log
import com.example.nika.android2.models.Restaurant

import java.util.ArrayList


class Repository  {

    companion object {

        private val arrrestaurant: ArrayList<Restaurant> = ArrayList<Restaurant>()
        fun getArrState(price:String,long:String,lath:String): ArrayList<Restaurant>  {          //ArrayList<restaurant> {
            arrrestaurant.clear()
//            val thread = Thread {
//
//                try {

           // 32.109333
           // 34.855499 //long
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
                    val client = OkHttpClient()
                    val request = Request.Builder()

                        .url("https://travel-advisor.p.rapidapi.com/restaurants/list-in-boundary?bl_latitude="+lath+"&tr_latitude=31.771959&bl_longitude="+long+"&tr_longitude=35.217018&restaurant_tagcategory_standalone=10591&restaurant_tagcategory=10591&limit=30&currency=USD&prices_restaurants="+price+"&open_now=false&lunit=km&lang=en_US")
                        .get()
                        .addHeader("X-RapidAPI-Host", "travel-advisor.p.rapidapi.com")
                        .addHeader("X-RapidAPI-Key", "1e07899177mshdf4a78b1466536ap158ba9jsn7dd02d903249")
                        .build()

                    val response = client.newCall(request).execute()
                    val root: JSONObject = JSONTokener(response.body()?.string()).nextValue() as JSONObject
                    val rootElement: JSONArray = root.get("data") as JSONArray

                    var id1 :Long
                    id1=0
                    var  entriesname  :String
                    var  entriesraw_ranking  :String
                    var  entriesaddress  :String
                    var  entriesphoto  :String
                    var  min_price  :Long =0

            for (i in 0 until rootElement.length()) {
                        val je = rootElement.getJSONObject(i)

//                        val obj: JsonObject = je.getJSONObject() //since you know it's a JsonObject




                        if(je.has("name")){
                             entriesname = je.get("name") as String
                        }else{
                                entriesname = "null"
                        }

                        if(je.has("raw_ranking")){
                            entriesraw_ranking = je.get("raw_ranking") as String
                        }else{
                            entriesraw_ranking = "null"
                        }

                        if(je.has("price")){//address
                            entriesaddress = je.get("price") as String
                            val list = entriesaddress.split('-')
                            min_price=(list[0].replace("$", "").replace(" ", "")).toLong()


                        }else{
                            entriesaddress = "null"
                        }


                        if(je.has("photo")){
                            val photo1_0: JSONObject = je.get("photo") as JSONObject
                            if(photo1_0.has("images")){
                                val photo1_1: JSONObject = photo1_0.get("images") as JSONObject

                                if(photo1_1.has("large")){
                                    val photo1_2: JSONObject = photo1_1.get("large") as JSONObject

                                    if(photo1_2.has("url")){
                                        entriesphoto = photo1_2.get("url") as String

                                    }
                                    else {entriesphoto= "null"}
                                }
                                else {entriesphoto= "null"}

                            }                                    else {entriesphoto= "null"}


                        }else{
                            entriesphoto = "null"
                        }

                        if(entriesname!="null" && entriesaddress!="null" ){
                            if(price!="-1"){
                                if(min_price.toLong()<=price.toLong()){
                                    arrrestaurant.add(Restaurant(entriesname, entriesraw_ranking, entriesaddress,entriesphoto,id1))
                                    id1=id1+1
                                }
                            }else{
                                arrrestaurant.add(Restaurant(entriesname, entriesraw_ranking, entriesaddress,entriesphoto,id1))
                                id1=id1+1
                            }
                        }
                    }
            return arrrestaurant;
        }
        }
    }



