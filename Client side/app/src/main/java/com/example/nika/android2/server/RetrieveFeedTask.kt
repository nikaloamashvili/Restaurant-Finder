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

class RetrieveFeedTask(bool:String,name:String,phone: String,age:String,address: String,email: String,admin: String  ) :
    AsyncTask<String?, Void?, String?>() {
    private val exception: Exception? = null
    private var modifiedSentence: String? = null
    private val iname :String =name;
    private val iphone :String =phone;
    private val iage :String =age;
    private val iadmin :String =admin;
    private val iaddress :String =address;
    private val iemail :String =email;
    private val ibool :String =bool;
    private lateinit var dataofuser:String ;
    private lateinit var sname :String ;
    private lateinit var sphone :String ;
    private lateinit var sage :String ;
    private lateinit var saddress :String ;
    private lateinit var semail :String ;
    private lateinit var sadmin :String ;

    private  var done :Int =0 ;
    companion object{
        private var user  : User = User("0","0","0","0","0","0");
        fun returnuser(): User {

            // var user :User = User("0","0","0","0","0")
            return user ;

        }
    }




    protected override fun doInBackground(vararg p0: String?): String? {
        var clientSocket: Socket? = null
        try {
            //192.168.1.11


            clientSocket = Socket("192.168.1.11", 10000)

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