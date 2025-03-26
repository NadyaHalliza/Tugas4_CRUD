package com.example.crudroomapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddCategoryActivity extends AppCompatActivity {
    private EditText editTextCategory;
    private Button btnSave;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        editTextCategory = findViewById(R.id.editTextCategory);
        btnSave = findViewById(R.id.btnSave);
        repository = new Repository(getApplication());

        btnSave.setOnClickListener(v -> {
            String categoryName = editTextCategory.getText().toString().trim();
            if (!categoryName.isEmpty()) {
                repository.insertCategory(new Category(categoryName));
                Toast.makeText(this, "Kategori ditambahkan", Toast.LENGTH_SHORT).show();
                finish(); // Tutup activity setelah menambahkan data
            } else {
                Toast.makeText(this, "Nama kategori tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
