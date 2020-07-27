package com.kmms.fkhr

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import kotlinx.android.synthetic.main.signup_page.*
import kotlinx.android.synthetic.main.student_login.*


class StudentLogin : AppCompatActivity() {

    private var flag: Boolean = false
    private lateinit var user: String
    private lateinit var pass: String
    var fbAuth = FirebaseAuth.getInstance()

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_login)
    }

    fun signin(view: View) {
        user = student_id_value.text.toString()
        pass = student_pass_value.text.toString()

        if (student_id_value.text.isEmpty() || student_pass_value.text.isEmpty()) {
            Toast.makeText(this, " Some Field is Missing or Incorrect ", Toast.LENGTH_LONG).show()
        } else {

            var email = user + "@gmail.com"
            fbAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, " Logged In ", Toast.LENGTH_LONG).show()
                        var intent = Intent(this, studentHome::class.java)
                        intent.putExtra("key",user)
                        // intent.putExtra("Detail",users)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, " Failed ", Toast.LENGTH_LONG).show()
                    }
                })
        }
    }

    fun processData(data: DataSnapshot) {
        for (child in data.children) {
            val users = child.getValue(student::class.java)!!
            Toast.makeText(this, "Data is being processed", Toast.LENGTH_LONG).show()

            if (users.MessNo.equals(user) && users.password.toString().equals(pass)) {
                flag = true
                var intent = Intent(this, PreviousMenu::class.java)
                // intent.putExtra("Detail",users)
                startActivity(intent)
            }
        }
        if (!flag) {
            Toast.makeText(this, " You did not Sign Up ", Toast.LENGTH_LONG).show()
        }
    }

    fun moveNext(view: View) {
        val i = Intent(this, Signup::class.java)
        startActivity(i)
    }
}