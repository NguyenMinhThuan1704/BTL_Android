package com.example.appdecorativelight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.appdecorativelight.Adapters.MainAdapter;
import com.example.appdecorativelight.Models.MainModel;
import com.example.appdecorativelight.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

            // Khai báo biến
            Toolbar tlb = (Toolbar) findViewById(R.id.toolbar);

            // Gán vào biến
            setSupportActionBar(tlb);

        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(R.drawable.denchum1, "Đèn chùm 1","15","Đèn treo phòng khách"));
        list.add(new MainModel(R.drawable.denchum2, "Đèn chùm 2","10","Đèn treo phòng ngủ"));
        list.add(new MainModel(R.drawable.denchum3, "Đèn chùm 3","5","Đèn treo phòng bếp"));

        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
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
            Toast.makeText(MainActivity.this, "My Orders", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, OrderActivity.class));

//        int id = item.getItemId();
//        switch (item.getItemId()){
//            case id == R.id.orders:
//                startActivity(new Intent(MainActivity.this, OrderActivity.class));
//                break;
//        }

        return super.onOptionsItemSelected(item);
    }
}