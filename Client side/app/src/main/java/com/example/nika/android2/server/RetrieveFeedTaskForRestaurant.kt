package com.example.nika.android2.server

import android.os.AsyncTask
import android.util.Log
import com.example.nika.android2.lazycolum.Repository
import com.example.nika.android2.models.Restaurant
import com.example.nika.android2.models.User
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.Socket
import java.util.ArrayList

class RetrieveFeedTaskForRestaurant(bool:String,  restaurant: Restaurant ,email: String ) :
    AsyncTask<String?, Void?, String?>() {
        private val exception: Exception? = null
        private var modifiedSentence: String? = null
        private val irestaurant :Restaurant =restaurant;
        private val ibool :String =bool;
        private lateinit var dataores:String ;
        private lateinit var sname :String ;
        private lateinit var sraw_ranking :String ;
        private lateinit var saddress :String ;
        private lateinit var sphoto :String ;
        private lateinit var suserEmail :String ;
        private var id: Long=0
        private val iemail :String =email;




    private  var done :Int =0 ;
        companion object{
            private val arrrestaurant: ArrayList<Restaurant> = ArrayList<Restaurant>()
            //private var restaurant  : Restaurant = Restaurant("0","0","0","0",4);
            fun getArrState(): ArrayList<Restaurant> {

                // var user :User = User("0","0","0","0","0")
                return arrrestaurant ;

            }
        }




        protected override fun doInBackground(vararg p0: String?): String? {
            var clientSocket: Socket? = null
            try {
                clientSocket = Socket("192.168.1.11", 10000)
                val outToServer = DataOutputStream(clientSocket.getOutputStream())
                val inFromServer = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
                if(ibool=="3"){
                    outToServer.writeBytes(ibool.trimIndent()+"\n")
                    outToServer.writeBytes(iemail.trimIndent()+"\n")
                }
                else if(ibool=="2"){
                    outToServer.writeBytes(ibool.trimIndent()+"\n")
                    outToServer.writeBytes(irestaurant.name.trimIndent()+"\n")
                    outToServer.writeBytes(irestaurant.raw_ranking.trimIndent()+"\n")
                    outToServer.writeBytes(irestaurant.address.trimIndent()+"\n")
                    outToServer.writeBytes(irestaurant.photo.trimIndent()+"\n")
                    if(RetrieveFeedTask.returnuser().admin.equals("1"))
                    {
                        outToServer.writeBytes("ads".trimIndent()+"\n")
                    }else{
                        outToServer.writeBytes(irestaurant.userEmail.trimIndent()+"\n")
                    }
                }else{

                    outToServer.writeBytes(ibool.trimIndent()+"\n")
                    outToServer.writeBytes(irestaurant.name.trimIndent()+"\n")
                    if(RetrieveFeedTask.returnuser().admin.equals("1"))
                    {


                        outToServer.writeBytes("ads".trimIndent()+"\n")
                    }else{


                        outToServer.writeBytes(irestaurant.userEmail.trimIndent()+"\n")
                    }
                }

                modifiedSentence = inFromServer.readLine()

            } catch (e: IOException) {
                e.printStackTrace()
            }
            return modifiedSentence
        }


        override fun onPostExecute(feed: String?) {

            if (feed != null) {
                if( !feed.equals("!")) {
                    arrrestaurant.clear()
                    dataores=feed;


                    val strs = dataores.split("*").toTypedArray()

                    //Log.d("kukunew", strs[0].toString())
                    //Log.d("kukunew", strs[1].toString())
                    //Log.d("kukunew", strs[2].toString())
                    //Log.d("kukunew", strs[5].toString())
                    var i :Int=0;
                    while(i<((strs.size))){
                        if(strs[i].equals("!")){
                            break;
                        }
                        this.sname=strs[i].toString();
                        this.sraw_ranking=strs[i+1].toString();
                        this.saddress=strs[i+2].toString();
                        this.sphoto=strs[i+3].toString();
                        this.suserEmail=strs[i+4].toString();
                        this.id=((i/5).toLong())

                        i=i+5;
                        arrrestaurant.add(Restaurant(sname, sraw_ranking, saddress,sphoto,id,suserEmail))
                    }
                    done=1;
                    // Log.d("kuku3", user.name.toString())
                }
            }
        }
    }
