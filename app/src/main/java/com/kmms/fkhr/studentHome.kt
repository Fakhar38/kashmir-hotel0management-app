package com.kmms.fkhr

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class studentHome : AppCompatActivity() {
    var spinner: Spinner? = null
    var user_id=""
    var totalmoney=0
    //    var listview: ListView? = null
    var recyclerView: RecyclerView? = null
    var total: TextView? = null
    var previousrecords: TextView? = null
    var attendenceCheckbox: CheckBox? = null
    private lateinit var database: DatabaseReference
    private lateinit var database_menue: DatabaseReference
    private lateinit var attendenceDatabase: DatabaseReference
    var MenuNames = ArrayList<String>()
    var SelectedItem: String = ""

    companion object{
        var selected_price_list=0
        var my_menu_name=""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_home)
        initialize()
        GetDatafromFireBase()
        attendenceCheckbox?.setOnCheckedChangeListener { buttonView, isChecked ->
            addAttendenceToDatabase(isChecked)
        }

        if (spinner != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, MenuNames)
            spinner!!.adapter = arrayAdapter

            spinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0) {
                        SelectedItem = MenuNames[position]
                        LoadDatatoList(MenuNames[position])
                        my_menu_name=MenuNames[position]
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }
    }

    private fun addAttendenceToDatabase(checked: Boolean) {
        val sdf = SimpleDateFormat("DD/MM/YY HH:mm")
        val currentDate = sdf.format(Date())
        var UID = FirebaseAuth.getInstance().currentUser!!.email
        var name = UID!!.split("@")
        var attendence: test
        if (checked) {
            attendence = test("true", currentDate)
        } else {
            attendence = test("false", currentDate)
        }
        attendenceDatabase.child("Attendance").child(name[0]!!).child(SelectedItem)
            .setValue(attendence)
            .addOnSuccessListener {
                Toast.makeText(this@studentHome, "Data Updated", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this@studentHome, "Unable to Add test", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun LoadDatatoList(s: String) {

        database.child(s).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val menu = dataSnapshot.getValue(MenuModel::class.java)
                var startTime = menu!!.startDateTime
                var EndTime = menu!!.endDateTime
                val priceArray =
                    arrayOf<Int>(menu!!.one, menu!!.two, menu!!.three, menu!!.four, menu!!.five)
                val menuArray = arrayOf<String>(
                    menu!!.menuitem1,
                    menu!!.menuitem2,
                    menu!!.menuitem3,
                    menu!!.menuitem4,
                    menu!!.menuitem5
                )
                val Adapter = ArrayAdapter<String>(
                    this@studentHome,
                    android.R.layout.simple_list_item_1, menuArray
                )
//                listview!!.adapter=Adapter

                callRecyclerViewAdapter(menuArray, priceArray)

                //getTimeCompare(startTime,EndTime)

            }
        })
    }

    private fun callRecyclerViewAdapter(
        menuArray: Array<String>,
        priceArray: Array<Int>
    ) {

        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = RecyclerAdapter(menuArray, priceArray,total)

    }

    private fun getTimeCompare(startTime: String?, endTime: String?) {
        val startTimeparts = startTime!!.split(":")
        val endTimeparts = endTime!!.split(":")
        val sdf = SimpleDateFormat("HH:mm")
        val currentDate = sdf.format(Date())
        val currentTimeparts = currentDate!!.split(":")
        if ((currentTimeparts[0].toInt() > startTimeparts[0].toInt()) && (currentTimeparts[0].toInt() < endTimeparts[0].toInt())) {
            attendenceCheckbox!!.setVisibility(View.VISIBLE)
        } else if ((currentTimeparts[0].toInt() == startTimeparts[0].toInt())) {
            if (currentTimeparts[1].toInt() >= startTimeparts[1].toInt()) {
                attendenceCheckbox!!.setVisibility(View.VISIBLE)
            }
        } else if ((currentTimeparts[0].toInt() == endTimeparts[0].toInt())) {
            if (currentTimeparts[1].toInt() <= endTimeparts[1].toInt()) {
                attendenceCheckbox!!.setVisibility(View.VISIBLE)
            }
        } else {
//            attendenceCheckbox!!.setVisibility(View.GONE)
        }
    }

    fun initialize() {
        spinner = findViewById<Spinner>(R.id.menuNameDropDown)
//        listview = findViewById(R.id.menuItemList)
        user_id=intent.getStringExtra("key")
        recyclerView = findViewById(R.id.myrecyclerview)
        previousrecords = findViewById(R.id.showhistory)
        total = findViewById(R.id.total_bill)
        database = FirebaseDatabase.getInstance().getReference("Menu")
        database_menue = FirebaseDatabase.getInstance().getReference("user_bill_records").child(user_id)
        attendenceDatabase = FirebaseDatabase.getInstance().reference
        MenuNames.add("Select")
        attendenceCheckbox = findViewById(R.id.attendence_checkbox)
        attendenceCheckbox!!.setVisibility(View.GONE)

    }

    fun GetDatafromFireBase() {

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (childSnapshot: DataSnapshot in dataSnapshot.children) {
                    if (childSnapshot.child("visible").getValue()!!.equals(true)) {
                        MenuNames.add(childSnapshot.key.toString())
                    }
                }
            }
        })
    }


    class RecyclerAdapter(
        val menuArray: Array<String>,
        val priceArray: Array<Int>,
        val total: TextView?
    ) : RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>() {
        var total_money=0
        init {
        }

        class PhotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var textView: TextView? = null
            var check_image: ImageView? = null

            init {
                textView = itemView.findViewById(R.id.checkenekekeke)
                check_image = itemView.findViewById(R.id.check_imageview)

            }
        }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PhotoHolder {
            val view =
                LayoutInflater.from(p0.context).inflate(R.layout.item_recycler_view, p0, false)
            return PhotoHolder(view)
        }

        override fun getItemCount(): Int {
            return menuArray.size
        }

        override fun onBindViewHolder(p0: PhotoHolder, p1: Int) {
            p0.textView?.text = menuArray[p1]+"  ( RS: "+priceArray[p1]+")"
            p0.itemView.setOnClickListener(View.OnClickListener {

                if (p0.check_image!!.visibility == View.GONE) {
                    p0.check_image!!.visibility = View.VISIBLE
                    total_money=total_money+priceArray[p1]
                    total?.text  = "Your Bill: "+total_money.toString()
                    selected_price_list=total_money

                } else {
                    p0.check_image!!.visibility = View.GONE
                    total_money=total_money-priceArray[p1]
                    selected_price_list=total_money
                    total?.text  =  "Your Bill: "+total_money.toString()
                }
            }
            )
        }

    }

    fun OrderFoodFunction(view: View) {

        if(selected_price_list==0 || my_menu_name.contentEquals(""))
            Toast.makeText(this,"Select atleast one item from Menu",Toast.LENGTH_SHORT).show()
        else
        {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val currentDate = sdf.format(Date())
            val today_order=FoodOrdered(my_menu_name, selected_price_list.toString(),currentDate)

            database_menue.child(System.currentTimeMillis().toString()).setValue(today_order).addOnCompleteListener(
                OnCompleteListener {
                    if(it.isSuccessful)
                        Toast.makeText(this@studentHome,"order Successful",Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    fun gotoRecordActivity(view: View) {

        val intent=Intent(this,StudentOrderRecord::class.java)
        intent.putExtra("key",user_id)
        startActivity(intent)

    }


}

