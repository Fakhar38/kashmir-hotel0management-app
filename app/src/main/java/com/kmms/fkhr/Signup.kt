package com.kmms.fkhr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.signup_page.*


class Signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)
        auth = FirebaseAuth.getInstance()
    }

    /* fun addData(view: View){
         val departmentStr = findViewById<TextView>(R.id.department_value).text.trim()
         val hostelnameStr = findViewById<TextView>(R.id.hostel_name_value).text.trim()
         val messnoStr = findViewById<TextView>(R.id.mess_no_value).text.trim()
         val nameStr = findViewById<TextView>(R.id.student_name_value).text.trim()
         val passwordStr = findViewById<TextView>(R.id.password_value).text.trim()
         val registrationnoStr = findViewById<TextView>(R.id.Regd_no_value).text.trim()
         val userIdStr = findViewById<TextView>(R.id.user_id_value).text.trim()
         val btnsignup = findViewById<Button>(R.id.button2)

         val fb = FirebaseDatabase.getInstance().reference
         val students = fb.child("Information/student")

         val value: Any = student1(
             departmentStr as String, hostelnameStr as String,
             messnoStr as String, nameStr as String, passwordStr as Int, registrationnoStr as String
             , userIdStr as String
         )
         btnsignup.setOnClickListener(object : View.OnClickListener{
             override fun onClick(v: View?) {
                 com.example.messmanagementsystem.student(departmentStr, hostelnameStr, messnoStr, nameStr,
                     passwordStr, registrationnoStr, userIdStr)
                 students.child(nameStr).setValue(com.example.messmanagementsystem.student::class.java)
             }
         })
     }*/
    fun submit(view: View) {
        val departmentStr = department_value.text.trim()
        val hostelnameStr = hostel_name_value.text.trim()
        val messnoStr = mess_no_value.text.trim()
        val nameStr = student_name_value.text.trim()
        val passwordStr = password_value.text.trim()
        val registrationnoStr = Regd_no_value.text.trim()
        val userIdStr = user_id_value.text.trim()
        val btnsignup = button2
        val table = "student"
        if (departmentStr.isEmpty() || hostelnameStr.isEmpty() || messnoStr.isEmpty() || passwordStr.isEmpty() || registrationnoStr.isEmpty() || userIdStr.isEmpty()) {
            Toast.makeText(this, "Some Field is Missing", Toast.LENGTH_LONG).show()
        } else {
            writetodatabase(
                departmentStr.toString(),
                hostelnameStr.toString(),
                messnoStr.toString(),
                nameStr.toString(),
                passwordStr.toString(),
                registrationnoStr.toString(),
                userIdStr.toString(),
                table
            )
        }
    }

    fun writetodatabase(
        departmentStr: String,
        hostelnameStr: String,
        messnoStr: String,
        nameStr: String,
        passwordStr: String,
        registrationnoStr: String,
        userIdStr: String,
        table: String
    ) {
        var email=registrationnoStr+"@gmail.com"
        auth.createUserWithEmailAndPassword(email, passwordStr).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid
                Toast.makeText(this, "Registered", Toast.LENGTH_LONG).show()
//                val dbRef=FirebaseDatabase.getInstance();
//                    val registerRef = dbRef.child("user").child(userId!!)
//                    val user = User(displayName.text.toString(), status.text.toString())
//                    registerRef.setValue(user).addOnSuccessListener(){
//                        val intent = Intent(this@Signup, LoginActivity::class.java)
//                        startActivity(intent)
//                        finish()
//                    }
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
            }
        }

//get database access
//        var f_b = FirebaseDatabase.getInstance().reference
//        val table = f_b.child("Hostel/$table")
//        //add student's record
//        val bart = table.child("$messnoStr")
//        bart.child("departmentStr").setValue("$departmentStr")
//        bart.child("hostelnameStr").setValue("$hostelnameStr")
//        bart.child("messnoStr").setValue("$messnoStr")
//        bart.child("nameStr").setValue("$nameStr")
//        bart.child("passwordStr").setValue("$passwordStr")
//        bart.child("registrationnoStr").setValue("$registrationnoStr")
//        bart.child("userIdStr").setValue("$userIdStr")
//
//
//        if (table.equals("Registered Users")) {
//            Toast.makeText(
//                this,
//                "You have successfully Submitted.We will notify you after you have Authenticated",
//                Toast.LENGTH_LONG
//            ).show()
//        }
    }


    fun deletefromdatabase(Phone: String) {

        //get database access
        var f_b = FirebaseDatabase.getInstance().getReference("Not Authenticated").child(Phone)
        f_b.removeValue()
    }


}