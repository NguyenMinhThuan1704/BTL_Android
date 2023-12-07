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
import android.widget.TextView;
import android.widget.Toast;
import com.example.appdecorativelight.Adapters.MainAdapter;
import com.example.appdecorativelight.Adapters.SlideImageAdapter;
import com.example.appdecorativelight.Models.MainModel;
import com.example.appdecorativelight.databinding.ActivityMainBinding;
import java.util.ArrayList;
import android.os.Handler;
import androidx.viewpager.widget.ViewPager;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    private ViewPager viewPager;
    private SlideImageAdapter slideImageAdapter;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toast.makeText(this, "Chào mừng bạn đến với website bán đèn trang trí", Toast.LENGTH_SHORT).show();

        viewPager = findViewById(R.id.viewPager);
        slideImageAdapter = new SlideImageAdapter(this);
        viewPager.setAdapter(slideImageAdapter);

        // Tự động lướt ảnh sau mỗi 3 giây
        final Handler handler = new Handler();
        final Runnable update = () -> {
            if (currentPage == slideImageAdapter.getCount()) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, true);
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 3000, 3000);

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

        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(R.drawable.denchum1, "Đèn chùm 1", "15", "Đèn treo phòng khách"));
        list.add(new MainModel(R.drawable.denchum2, "Đèn chùm 2", "10", "Đèn treo phòng ngủ"));
        list.add(new MainModel(R.drawable.denchum3, "Đèn chùm 3", "5", "Đèn treo phòng bếp"));
        list.add(new MainModel(R.drawable.denchum4, "Đèn chùm 4", "20", "Đèn treo phòng khách"));
        list.add(new MainModel(R.drawable.denchum5, "Đèn chùm 5", "25", "Đèn treo phòng bếp"));
        ArrayList<MainModel> list1 = new ArrayList<>();
        list1.add(new MainModel(R.drawable.dentha1, "Đèn thả 1", "15", "Đèn treo phòng khách"));
        list1.add(new MainModel(R.drawable.dentha2, "Đèn thả 2", "10", "Đèn treo phòng ngủ"));
        list1.add(new MainModel(R.drawable.dentha3, "Đèn thả 3", "5", "Đèn treo phòng bếp"));
        list1.add(new MainModel(R.drawable.dentha4, "Đèn thả 4", "20", "Đèn treo phòng khách"));
        list1.add(new MainModel(R.drawable.dentha5, "Đèn thả 5", "25", "Đèn treo phòng bếp"));
        ArrayList<MainModel> list2 = new ArrayList<>();
        list2.add(new MainModel(R.drawable.dentuong1, "Đèn tường 1", "15", "Đèn treo phòng khách"));
        list2.add(new MainModel(R.drawable.dentuong2, "Đèn tường 2", "10", "Đèn treo phòng ngủ"));
        list2.add(new MainModel(R.drawable.dentuong3, "Đèn tường 3", "5", "Đèn treo phòng bếp"));
        list2.add(new MainModel(R.drawable.dentuong4, "Đèn tường 4", "20", "Đèn treo phòng khách"));
        list2.add(new MainModel(R.drawable.dentuong5, "Đèn tường 5", "25", "Đèn treo phòng bếp"));

        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

        MainAdapter adapter1 = new MainAdapter(list1, this);
        binding.recyclerview1.setAdapter(adapter1);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        binding.recyclerview1.setLayoutManager(layoutManager1);

        MainAdapter adapter2 = new MainAdapter(list2, this);
        binding.recyclerview2.setAdapter(adapter2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        binding.recyclerview2.setLayoutManager(layoutManager2);
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
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        if (id == R.id.orders1)
            startActivity(new Intent(MainActivity.this, IntroduceActivity.class));
        if (id == R.id.orders2)
            startActivity(new Intent(MainActivity.this, OrderActivity.class));
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
        Intent intent = new Intent(MainActivity.this, destinationActivity);
        startActivity(intent);
    }
}