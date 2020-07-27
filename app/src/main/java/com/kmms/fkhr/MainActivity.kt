package com.kmms.fkhr

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun moveNext(view : View){
        val i = Intent(this,Home::class.java)
        startActivity(i)
    }

}
