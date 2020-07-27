package com.kmms.fkhr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.google.firebase.database.*


class PreviousMenu : AppCompatActivity() {
    var spinner: Spinner? = null
    var listview: ListView? = null
    private var activateButton: Button? = null
    private lateinit var database: DatabaseReference
    var MenuNames = ArrayList<String>()
    var SelectedItem: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.previousmenu)
        initialize()
        GetDatafromFireBase()

        activateButton!!.setOnClickListener {
            Toast.makeText(this@PreviousMenu,"Enter",Toast.LENGTH_SHORT).show()
            changeValueOfVisibleMenu()
        }

        if (spinner != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, MenuNames)
            spinner!!.adapter = arrayAdapter
            spinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    SelectedItem=MenuNames[position]
                    Toast.makeText(this@PreviousMenu, MenuNames[position], Toast.LENGTH_SHORT).show()
                    if (position != 0) {
                        LoadDatatoList(MenuNames[position])
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }
    }

    private fun changeValueOfVisibleMenu() {
        database.child(SelectedItem).child("visible").setValue(true)
            .addOnSuccessListener {
                Toast.makeText(this@PreviousMenu,"Success",Toast.LENGTH_SHORT).show()
                changeAllOtherValues(SelectedItem)
            }
            .addOnFailureListener {
                Toast.makeText(this@PreviousMenu,"Fail",Toast.LENGTH_SHORT).show()
            }
    }

    private fun changeAllOtherValues(menuName: String) {
        database.addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Toast.makeText(this@PreviousMenu,"Cancel",Toast.LENGTH_SHORT).show()
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (childSnapshot: DataSnapshot in dataSnapshot.children){
                        if(!childSnapshot.key.toString().equals(menuName)){
                            database.child(childSnapshot.key.toString()).child("visible").setValue(false)
                        }
                    }
                }
            }
        )
    }
    private fun LoadDatatoList(s: String) {
        database.child(s).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val menu=dataSnapshot.getValue(MenuModel::class.java)
                val menuArray =arrayOf<String>(menu!!.menuitem1,menu!!.menuitem2,menu!!.menuitem3,menu!!.menuitem4,menu!!.menuitem5)
                val Adapter = ArrayAdapter<String>(this@PreviousMenu,
                    android.R.layout.simple_list_item_1, menuArray)
                listview!!.adapter=Adapter

            }
        })
    }

    fun initialize() {

        spinner = findViewById<Spinner>(R.id.menuNameDropDown)
        listview = findViewById(R.id.menuItemList)
        activateButton=findViewById(R.id.activate_btn)
        database = FirebaseDatabase.getInstance().getReference("Menu")
        MenuNames.add("Select")

    }

    fun GetDatafromFireBase() {

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                MenuNames.clear()
                MenuNames.add("Select")
                for (childSnapshot: DataSnapshot in dataSnapshot.children) {
                    MenuNames.add(childSnapshot.key.toString())

                }
                Toast.makeText(this@PreviousMenu,MenuNames.size.toString(),Toast.LENGTH_SHORT).show()
            }
        })
    }
}