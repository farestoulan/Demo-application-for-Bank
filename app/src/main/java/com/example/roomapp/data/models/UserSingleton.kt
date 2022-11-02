package com.example.roomapp.data.models


object UserSingleton {


    @JvmStatic
    var id :Int = 0
        private set
    @JvmStatic
    var email :String = ""
        private set
    @JvmStatic
    var password :String = ""
        private set


    fun setData(email:String , password :String , id:Int){


         UserSingleton.id = id
        UserSingleton.email = email
        UserSingleton.password = password

  }

    @JvmName("getEmail1")
    fun getEmail():String{

        return email
    }

    @JvmName("getPassword1")
    fun getPassword():String{

        return password
    }

    fun getID():Int{

        return id
    }


}