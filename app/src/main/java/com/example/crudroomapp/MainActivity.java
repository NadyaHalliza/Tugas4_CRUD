package com.example.crudroomapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Repository repository;
    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private MaterialButton btnAddCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new Repository(getApplication());
        recyclerView = findViewById(R.id.recyclerViewCategories);
        btnAddCategory = findViewById(R.id.btnAddCategory);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAddCategory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddCategoryActivity.class);
            startActivity(intent);
        });

        adapter = new CategoryAdapter(this, new ArrayList<>(), category -> {
            Intent intent = new Intent(MainActivity.this, DetailCategoryActivity.class);
            intent.putExtra("categoryId", category.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        loadCategories();
    }

    protected void onResume() {
        super.onResume();
        loadCategories();  // Refresh list kategori setelah kembali ke MainActivity
    }

    private void loadCategories() {
        repository.getAllCategories().observe(this, categories -> {
            adapter.setCategoryList(categories);
            adapter.notifyDataSetChanged();
        });
    }


}
