package com.example.appdecorativelight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.appdecorativelight.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    DBHelper helper = new DBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toast.makeText(this, "Chi tiết sản phẩm", Toast.LENGTH_SHORT).show();

        Toolbar tlb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tlb);
        int giaBanDau;

        if (getIntent().getIntExtra("type", 0) == 1) {

            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String lightname = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%s", price));
            binding.nameProduct.setText(lightname);
            binding.detailDescription.setText(description);

            final int[] soLuong = {1}; // Số lượng ban đầu
            giaBanDau = Integer.parseInt(getIntent().getStringExtra("price"));

            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    soLuong[0]++;
                    capNhatSoLuongVaGia(soLuong[0], giaBanDau);
                }
            });

            binding.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (soLuong[0] > 1) {
                        soLuong[0]--;
                        capNhatSoLuongVaGia(soLuong[0], giaBanDau);
                    }
                }
            });

            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = binding.nameBox.getText().toString();
                    String phone = binding.phoneBox.getText().toString();
                    if (name.equals("") || phone.equals(""))
                        Toast.makeText(DetailActivity.this, "Tất cả các trường là bắt buộc", Toast.LENGTH_SHORT).show();
                    else {
                        int giaTong = Integer.parseInt(binding.priceLbl.getText().toString()); // Lấy giá đã tính toán
                        boolean isInserted = helper.insertOrder(
                                binding.nameBox.getText().toString(),
                                binding.phoneBox.getText().toString(),
                                giaTong,
                                image,
                                description,
                                lightname,
                                Integer.parseInt(binding.quantity.getText().toString())
                        );

                        if (isInserted) {
                            Toast.makeText(DetailActivity.this, "Mua thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DetailActivity.this, MainActivity.class));
                        } else
                            Toast.makeText(DetailActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);
            int image = cursor.getInt(4);
            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%s", cursor.getInt(3)));
            binding.quantity.setText(cursor.getString(5));

            binding.nameProduct.setText(cursor.getString(7));
            binding.detailDescription.setText(cursor.getString(6));

            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertBtn.setText("Cập nhật");

            final int[] soLuong = {Integer.parseInt(binding.quantity.getText().toString())};
            giaBanDau = Integer.parseInt(binding.priceLbl.getText().toString()) / soLuong[0];

            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    soLuong[0]++;
                    capNhatSoLuongVaGia(soLuong[0], giaBanDau);
                }
            });

            binding.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (soLuong[0] > 1) {
                        soLuong[0]--;
                        capNhatSoLuongVaGia(soLuong[0], giaBanDau);
                    }
                }
            });
            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = binding.nameBox.getText().toString();
                    String phone = binding.phoneBox.getText().toString();
                    if (name.equals("") || phone.equals(""))
                        Toast.makeText(DetailActivity.this, "Tất cả các trường là bắt buộc", Toast.LENGTH_SHORT).show();
                    else {
                        boolean isUpdated = helper.updateOrder(
                                binding.nameBox.getText().toString(),
                                binding.phoneBox.getText().toString(),
                                Integer.parseInt(binding.priceLbl.getText().toString()),
                                image,
                                binding.detailDescription.getText().toString(),
                                binding.nameProduct.getText().toString(),
                                Integer.parseInt(binding.quantity.getText().toString()),
                                id
                        );
                        if (isUpdated){
                            Toast.makeText(DetailActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DetailActivity.this, OrderActivity.class));}
                        else
                            Toast.makeText(DetailActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void capNhatSoLuongVaGia(int soLuong, int giaBanDau) {
        binding.quantity.setText(String.valueOf(soLuong));

        // Cập nhật giá dựa trên giá ban đầu
        int giaTong = giaBanDau * soLuong;
        binding.priceLbl.setText(String.valueOf(giaTong));

        // Cập nhật giá trong trường hợp bạn đang ở trang hóa đơn
        if (getIntent().getIntExtra("type", 0) != 1) {
            binding.priceLbl.setText(String.valueOf(giaTong));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.detail_home)
            startActivity(new Intent(DetailActivity.this, MainActivity.class));
        if (id == R.id.detail_orders)
            startActivity(new Intent(DetailActivity.this, OrderActivity.class));
        return super.onOptionsItemSelected(item);
    }
}