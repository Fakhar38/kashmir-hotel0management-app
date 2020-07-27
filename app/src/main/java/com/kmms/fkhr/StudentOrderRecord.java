package com.kmms.fkhr;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentOrderRecord extends AppCompatActivity {

    DatabaseReference firebaseDatabase;
    RecyclerView recyclerView;
    List<FoodOrdered> mylist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_order_record);

        recyclerView=findViewById(R.id.order_recyclerview);
        getDataFromDatabase();

    }

    private void getDataFromDatabase() {

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("user_bill_records").child(getIntent().getStringExtra("key"));
        firebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();

                for (DataSnapshot next : snapshotIterator) {
                    mylist.add(next.getValue(FoodOrdered.class));
                    if(mylist.size()==dataSnapshot.getChildrenCount())
                    {
                        inflateRecyclerView();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inflateRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter());
    }

    public class  MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_orders,viewGroup,false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            myViewHolder.name.setText("Order Name: "+mylist.get(i).menu_name);
            myViewHolder.price.setText("Order Price: "+mylist.get(i).total_price);
            myViewHolder.date.setText("Order Date: "+mylist.get(i).order_date);
        }

        @Override
        public int getItemCount() {
            return mylist.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView name,price,date;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                name=itemView.findViewById(R.id.menu_nameo);
                price=itemView.findViewById(R.id.menu_priceo);
                date=itemView.findViewById(R.id.menu_dateo);
            }
        }
    }
}