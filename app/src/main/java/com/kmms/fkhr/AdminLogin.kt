package com.kmms.fkhr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.Toast


class AdminLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_login)

    }
    fun adminLogin(view: View){
        val UserName = findViewById<EditText>(R.id.admin_id_value).text.toString()
        val Password=findViewById<EditText>(R.id.admin_pass_value).text.toString()
        if(UserName.equals("admin") && Password.equals("12345")){
            val i = Intent(this,AdminHome::class.java)
            startActivity(i)
        }
        else
        {
            Toast.makeText(this,"Please enter the correct Username and Password", Toast.LENGTH_SHORT)
        }

    }

}