package com.kmms.fkhr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View


class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

    }
    fun gotoAdmin(view: View){
        val i = Intent(this,AdminLogin::class.java)
        startActivity(i)
    }
    fun gotoStudent(view: View){
        val i = Intent(this,StudentLogin::class.java)
        startActivity(i)
    }
}