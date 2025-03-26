package com.example.crudroomapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailCategoryActivity extends AppCompatActivity {
    private TextView textViewCategoryName;
    private Button btnBack, btnEdit, btnDelete;
    private Repository repository;
    private int categoryId;
    private EditText editTextCategoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);

        // Inisialisasi komponen UI
        textViewCategoryName = findViewById(R.id.textViewCategoryName);
        btnBack = findViewById(R.id.btnBack);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        editTextCategoryName = findViewById(R.id.editTextCategoryName); // Diperbaiki

        repository = new Repository(getApplication());

        // Mendapatkan ID kategori dari intent
        Intent intent = getIntent();
        categoryId = intent.getIntExtra("categoryId", -1);
        String categoryName = intent.getStringExtra("categoryName");

        if (categoryName != null) {
            editTextCategoryName.setText(categoryName); // Pastikan ini tidak di dalam blok lain
        }

        if (categoryId != -1) {
            repository.getCategoryById(categoryId).observe(this, category -> {
                if (category != null) {
                    textViewCategoryName.setText(category.getCategoryName());
                }
            });
        }

        // Tombol kembali untuk menutup activity
        btnBack.setOnClickListener(v -> finish());

        // Edit kategori
        btnEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(DetailCategoryActivity.this, EditCategoryActivity.class);
            editIntent.putExtra("categoryId", categoryId);
            editIntent.putExtra("categoryName", editTextCategoryName.getText().toString());
            startActivity(editIntent);
        });

        // Hapus kategori
        btnDelete.setOnClickListener(v -> {
            repository.getCategoryById(categoryId).observe(this, category -> {
                if (category != null) {
                    repository.deleteCategory(categoryId);
                    finish(); // Tutup activity setelah kategori dihapus
                }
            });
        });

        // Aktifkan tombol kembali di action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Tutup activity saat tombol back ditekan
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
