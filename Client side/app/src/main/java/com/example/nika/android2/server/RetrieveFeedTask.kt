package com.example.nika.android2.server

import android.os.AsyncTask
import android.util.Log
import com.example.nika.android2.models.User
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.Socket

class RetrieveFeedTask() : AsyncTask<String?, Void?, String?>() {
    private val exception: Exception? = null
    private var modifiedSentence: String? = null
    private lateinit var iname :String ;
    private lateinit var iphone :String ;
    private lateinit var iage :String ;
    private lateinit var iadmin :String ;
    private lateinit var iaddress :String ;
    private lateinit var iemail :String ;
    private lateinit var ibool :String;

    private lateinit var dataofuser:String ;
    private lateinit var sname :String ;
    private lateinit var sphone :String ;
    private lateinit var sage :String ;
    private lateinit var saddress :String ;
    private lateinit var semail :String ;
    private lateinit var sadmin :String ;

    private  var done :Int =0 ;

    constructor(bool:String,user: User) : this() {
      iname  = user.name;
      iphone  = user.phone;
      iage  = user.age;
     iadmin  = user.admin;
    iaddress = user.address;
        iemail  = user.mail;
     ibool  =bool;
    }

    constructor(bool:String,mail:String) : this() {
          ibool  =bool;
          iemail =mail;
    }



    companion object{
        private var user  : User = User("0","0","0","0","0","0");
        fun returnuser(): User {
            return user ;

        }
    }




    protected override fun doInBackground(vararg p0: String?): String? {
        var clientSocket: Socket? = null
        try {

            clientSocket = Socket("", 10000)//enter your pc ip here

            val outToServer = DataOutputStream(clientSocket.getOutputStream())

            val inFromServer = BufferedReader(InputStreamReader(clientSocket.getInputStream()))

            if(ibool=="1"){
                outToServer.writeBytes(ibool.trimIndent()+"\n")
                outToServer.writeBytes(iemail.trimIndent()+"\n")
            }
            else{
                outToServer.writeBytes(ibool.trimIndent()+"\n")
                outToServer.writeBytes(iname.trimIndent()+"\n")
                outToServer.writeBytes(iphone.trimIndent()+"\n")
                outToServer.writeBytes(iage.trimIndent()+"\n")
                outToServer.writeBytes(iaddress.trimIndent()+"\n")
                outToServer.writeBytes(iemail.trimIndent()+"\n")
                outToServer.writeBytes(iadmin.trimIndent()+"\n")
            }

            modifiedSentence = inFromServer.readLine()

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return modifiedSentence
    }


    override fun onPostExecute(feed: String?) {

        if(feed!=null){

        dataofuser=feed;
        val strs = dataofuser.split(" ").toTypedArray()



        this.sname=strs[0].toString();
        this.sphone=strs[1].toString();
        this.sage=strs[2].toString();
        this.saddress=strs[3].toString();
        this.semail=strs[4].toString();
        this.sadmin=strs[5].toString();
        done=1;

        user  = User(sname,sphone,sage,saddress,semail,sadmin)

        // Log.d("kuku3", user.name.toString())
    }}


}