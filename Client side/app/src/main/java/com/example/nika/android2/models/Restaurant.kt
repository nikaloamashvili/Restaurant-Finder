package com.example.nika.android2.models

import java.io.Serializable

/**
 * Created by EP on 18/07/2017.
 */
class Restaurant : Serializable {
    var name: String
        private set
    var raw_ranking: String
        private set
    lateinit var address: String
        private set
    lateinit var photo: String
        private set
    lateinit var userEmail: String
    var id: Long=0
    //var min_p: Long=0


    constructor(name: String, raw_ranking: String) {
        this.name = name
        this.raw_ranking = raw_ranking
    }

    constructor(name: String,  raw_ranking: String, address: String, photo: String,id:Long) {
        this.name = name
        this.raw_ranking = raw_ranking
        this.address = address
        this.photo = photo
        this.id = id
    }
    constructor(name: String,  raw_ranking: String, address: String, photo: String,id:Long,userEmail:String) {
        this.name = name
        this.raw_ranking = raw_ranking
        this.address = address
        this.photo = photo
        this.userEmail = userEmail;
        this.id = id
    }

}