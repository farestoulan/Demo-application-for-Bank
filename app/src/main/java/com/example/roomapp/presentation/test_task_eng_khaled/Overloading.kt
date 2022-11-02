package com.example.roomapp.presentation.test_task_eng_khaled

class Overloading {

    fun submishin( a :Int, b:Int):Int{

        val x = a+b
        return x
    }

    fun submishin(c :Float, d:Float,e:Float):Float{
        val y = c + d+ e
        return y

    }

    fun submishin(c :Int, d:Int,e:Int):Float{
        val y = c + d+ e
        return y.toFloat()

    }

}