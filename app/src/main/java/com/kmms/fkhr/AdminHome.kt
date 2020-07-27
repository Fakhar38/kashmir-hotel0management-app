package com.kmms.fkhr

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import java.io.File
import java.io.FileWriter
import java.util.*
import kotlin.collections.ArrayList


class AdminHome : AppCompatActivity() {

    private var uploadButton: Button? = null
    private var PMenuButton: Button? = null
    private var Attendance_Butoon: Button? = null
    private var menu_Name: EditText? = null
    private var menuItem1: EditText? = null
    private var price1: EditText? = null
    private var price2: EditText? = null
    private var price3: EditText? = null
    private var price4: EditText? = null
    private var price5: EditText? = null
    private var menuItem2: EditText? = null
    private var menuItem3: EditText? = null
    private var menuItem4: EditText? = null
    private var menuItem5: EditText? = null
    private var startDateTime: EditText? = null
    private var endDateTime: EditText? = null
    private lateinit var database: DatabaseReference
    private val CSV_HEADER = "Mess Number,Menu Name,isAttended,Date"
    var attendenceArray = ArrayList<testAdd>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_home)
        initialize()
        PMenuButton!!.setOnClickListener {
            var intent = Intent(this, PreviousMenu::class.java)
            // intent.putExtra("Detail",users)
            startActivity(intent)
        }
        startDateTime!!.setOnClickListener{
            showTimePicker(startDateTime!!)
        }
        endDateTime!!.setOnClickListener{
            showTimePicker(endDateTime!!)
        }
        uploadButton!!.setOnClickListener {
            if (menu_Name!!.text.toString().trim().equals("")||menuItem1!!.text.toString().trim().equals("") || menuItem2!!.text.toString().trim().equals("") || menuItem3!!.text.toString().trim().equals(
                    ""
                ) || menuItem4!!.text.toString().trim().equals("") || menuItem5!!.text.toString().trim().equals("")||price1!!.text.toString().trim().equals("")||price2!!.text.toString().trim().equals("") || price3!!.text.toString().trim().equals("") || price4!!.text.toString().trim().equals(
                    ""
                ) || price4!!.text.toString().trim().equals("") || price5!!.text.toString().trim().equals("")
            ) {
                Toast.makeText(this@AdminHome, "Please Fill All the Fields", Toast.LENGTH_SHORT).show()
            } else {
                StoreDatatoFireBase()
            }
        }
//        attendence_btn.setOnClickListener {
//            getAttendenceFromDataBase()
//        }
    }


    private fun showTimePicker(
        dateTime: EditText
    ) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)

        val tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener(function = { view, h, m ->

            dateTime.setText(h.toString()+":"+m)

        }),hour,minute,false)

        tpd.show()
    }

    fun initialize() {
        uploadButton = findViewById(R.id.upload_btn)
        PMenuButton=findViewById(R.id.previousMenu_btn)
//        Attendance_Butoon = findViewById(R.id.attendence_btn)
        menu_Name = findViewById(R.id.menu_name)
        price1 = findViewById(R.id.price11)
        price2 = findViewById(R.id.price22)
        price3 = findViewById(R.id.price33)
        price4 = findViewById(R.id.price44)
        price5 = findViewById(R.id.price55)
        menuItem1 = findViewById(R.id.menuitem1)
        menuItem2 = findViewById(R.id.menuitem2)
        menuItem3 = findViewById(R.id.menuitem3)
        menuItem4 = findViewById(R.id.menuitem4)
        menuItem5 = findViewById(R.id.menuitem5)
        startDateTime=findViewById(R.id.startdatetime)
        endDateTime=findViewById(R.id.enddatetime)
        startDateTime!!.isFocusable=false
        endDateTime!!.isFocusable=false
        database = FirebaseDatabase.getInstance().reference
    }

    private fun StoreDatatoFireBase() {
        var menu=MenuModel(
            menuItem1!!.text.toString(),
            menuItem2!!.text.toString(),
            menuItem3!!.text.toString(),
            menuItem4!!.text.toString(),
            menuItem5!!.text.toString(),
           Integer.valueOf(price1!!.text.toString()) ,
            Integer.valueOf(price2!!.text.toString()) ,
            Integer.valueOf(price3!!.text.toString()) ,
            Integer.valueOf(price4!!.text.toString()) ,
            Integer.valueOf(price5!!.text.toString()) ,
            true,
            startDateTime!!.text.toString(),
            endDateTime!!.text.toString()
        )
        var MenuName=menu_Name!!.text.toString().trim()
        database.child("Menu").child(MenuName).setValue(menu)
            .addOnSuccessListener {
                Toast.makeText(this@AdminHome, "Menu Added", Toast.LENGTH_SHORT).show()
                menuItem1!!.setText("")
                menuItem2!!.setText("")
                menuItem3!!.setText("")
                menuItem4!!.setText("")
                menuItem5!!.setText("")
                menu_Name!!.setText("")
                startDateTime!!.setText("")
                endDateTime!!.setText("")
                changeAllOtherValues(MenuName)
        }
            .addOnFailureListener {
                Toast.makeText(this@AdminHome, "Unable to Add Menu", Toast.LENGTH_SHORT).show()
            }
    }

    private fun changeAllOtherValues(menuName: String) {
        database.child("Menu").addValueEventListener(
            object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (childSnapshot: DataSnapshot in dataSnapshot.children){
                        if(!childSnapshot.key.toString().equals(menuName)){
                            database.child("Menu").child(childSnapshot.key.toString()).child("visible").setValue(false)
                        }
                    }
                }
            }
        )
    }
    private fun exportDataToCSV(){
//        val customers = Arrays.asList(
//            test("true","12/2/19 12:50"),
//            test("false","12/2/19 12:52"),
//            test("false","12/2/19 12:53"),
//            test("false","12/2/19 12:54"),
//            test("false","12/2/19 12:55"))

        val folder = File(Environment.getExternalStorageDirectory().absolutePath+"/MessSystem")
        var `var` = false
        if (!folder.exists())
            `var` = folder.mkdir()

       var fileWriter: FileWriter? = null

        try {
            fileWriter = FileWriter(folder.toString()+"/test.csv")

            fileWriter.append(CSV_HEADER)
            fileWriter.append('\n')


            for (array in attendenceArray) {
                fileWriter.append(array.name)
                fileWriter.append(',')
                fileWriter.append(array.menu)
                fileWriter.append(',')
                fileWriter.append(array.IsAttended)
                fileWriter.append(',')
                fileWriter.append(array.date)
                fileWriter.append('\n')
            }

            Toast.makeText(this@AdminHome, "Suceess", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this@AdminHome, "Error In Writing", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        } finally {
            fileWriter!!.close()
        }
    }

    private fun getAttendenceFromDataBase() {
        database.child("Attendance").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(childSnapshot: DataSnapshot in dataSnapshot.children){
                    var username=childSnapshot.key.toString()
                    Toast.makeText(this@AdminHome,attendenceArray.size.toString()+"->"+username,Toast.LENGTH_LONG).show()
                    for(ChildSnapshot: DataSnapshot in childSnapshot.children){
                        var menu=ChildSnapshot.key.toString()
                        Toast.makeText(this@AdminHome,attendenceArray.size.toString()+"->"+menu,Toast.LENGTH_LONG).show()
                            val attendence=ChildSnapshot.getValue(test::class.java)
                            attendenceArray.add(
                                testAdd(
                                    attendence!!.isAttended,
                                    attendence!!.date,
                                    username,
                                    menu
                                )
                            )

                    }
                    exportDataToCSV()
                }


            }
        })
    }
}