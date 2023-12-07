package com.example.appdecorativelight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RefundActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);

        Toast.makeText(this, "Chào mừng bạn đến với trang đổi trả hàng và hoàn tiền", Toast.LENGTH_SHORT).show();


        Toolbar tlb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tlb);

        TextView sp1 = findViewById(R.id.sp1);
        TextView sp_1 = findViewById(R.id.sp_1);
        TextView sp2 = findViewById(R.id.sp2);
        TextView sp_2 = findViewById(R.id.sp_2);
        TextView sp3 = findViewById(R.id.sp3);
        TextView sp_3 = findViewById(R.id.sp_3);
        TextView sp4 = findViewById(R.id.sp4);
        TextView sp5 = findViewById(R.id.sp5);
        TextView sp_5 = findViewById(R.id.sp_5);

        sp1.setOnClickListener(this);
        sp_1.setOnClickListener(this);
        sp2.setOnClickListener(this);
        sp_2.setOnClickListener(this);
        sp3.setOnClickListener(this);
        sp_3.setOnClickListener(this);
        sp4.setOnClickListener(this);
        sp5.setOnClickListener(this);
        sp_5.setOnClickListener(this);
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
            startActivity(new Intent(RefundActivity.this, MainActivity.class));
        if (id == R.id.orders1)
            startActivity(new Intent(RefundActivity.this, IntroduceActivity.class));
        if (id == R.id.orders2)
            startActivity(new Intent(RefundActivity.this, OrderActivity.class));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.sp1 || viewId == R.id.sp_1) {
            openActivity(GuaranteeActivity.class);
        } else if (viewId == R.id.sp2 || viewId == R.id.sp_2) {
            openActivity(RegulationsActivity.class);
        } else if (viewId == R.id.sp3 || viewId == R.id.sp_3) {
            openActivity(DeliveryActivity.class);
        } else if (viewId == R.id.sp4) {
            openActivity(RefundActivity.class);
        } else if (viewId == R.id.sp5 || viewId == R.id.sp_5) {
            openActivity(SecurityActivity.class);
        }
    }

    private void openActivity(Class<?> destinationActivity) {
        Intent intent = new Intent(RefundActivity.this, destinationActivity);
        startActivity(intent);
    }
}