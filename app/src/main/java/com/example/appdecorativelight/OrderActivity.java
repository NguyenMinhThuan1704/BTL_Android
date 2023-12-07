package com.example.appdecorativelight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdecorativelight.Adapters.OrdersAdapter;
import com.example.appdecorativelight.Models.OrdersModel;
import com.example.appdecorativelight.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityOrderBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toast.makeText(this, "Chào mừng bạn đến với hóa đơn", Toast.LENGTH_SHORT).show();

        Toolbar tlb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tlb);

        TextView sp1 = findViewById(R.id.sp1);
        TextView sp2 = findViewById(R.id.sp2);
        TextView sp3 = findViewById(R.id.sp3);
        TextView sp4 = findViewById(R.id.sp4);
        TextView sp5 = findViewById(R.id.sp5);

        sp1.setOnClickListener(this);
        sp2.setOnClickListener(this);
        sp3.setOnClickListener(this);
        sp4.setOnClickListener(this);
        sp5.setOnClickListener(this);

        DBHelper helper = new DBHelper(this);
        ArrayList<OrdersModel> list = helper.getOrders();

        OrdersAdapter adapter = new OrdersAdapter(list, this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.orders)
            startActivity(new Intent(OrderActivity.this, MainActivity.class));
        if (id == R.id.orders1)
            startActivity(new Intent(OrderActivity.this, IntroduceActivity.class));
        if (id == R.id.orders2)
            startActivity(new Intent(OrderActivity.this, OrderActivity.class));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.sp1) {
            openActivity(GuaranteeActivity.class);
        } else if (viewId == R.id.sp2) {
            openActivity(RegulationsActivity.class);
        } else if (viewId == R.id.sp3) {
            openActivity(DeliveryActivity.class);
        } else if (viewId == R.id.sp4) {
            openActivity(RefundActivity.class);
        } else if (viewId == R.id.sp5) {
            openActivity(SecurityActivity.class);
        }
    }

    private void openActivity(Class<?> destinationActivity) {
        Intent intent = new Intent(OrderActivity.this, destinationActivity);
        startActivity(intent);
    }
}