package com.example.nika.android2.server

import android.os.AsyncTask
import com.example.nika.android2.models.Restaurant
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.Socket
import java.util.ArrayList

class RetrieveFeedTaskForRestaurant() :
    AsyncTask<String?, Void?, String?>() {
        private val exception: Exception? = null
        private var modifiedSentence: String? = null
        private lateinit var irestaurant :Restaurant ;
        private lateinit var ibool :String ;
        private lateinit var dataores:String ;
        private lateinit var sname :String ;
        private lateinit var sraw_ranking :String ;
        private lateinit var saddress :String ;
        private lateinit var sphoto :String ;
        private lateinit var suserEmail :String ;
        private var id: Long=0
        private lateinit var iemail :String ;

    private  var done :Int =0 ;
        companion object{
            private val arrrestaurant: ArrayList<Restaurant> = ArrayList<Restaurant>()
            fun getArrState(): ArrayList<Restaurant> {
                return arrrestaurant ;
            }
        }

    constructor(bool:String,  restaurant: Restaurant ,email: String ) : this() {
        irestaurant=restaurant;
        iemail  = email;
        ibool  =bool;
    }

    constructor(bool:String, email: String ) : this() {
        iemail  = email;
        ibool  =bool;
    }


        protected override fun doInBackground(vararg p0: String?): String? {
            var clientSocket: Socket? = null
            try {
                //172.16.74.157
                    //192.168.1.11
                clientSocket = Socket("", 10000)//enter your pc ip here
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
