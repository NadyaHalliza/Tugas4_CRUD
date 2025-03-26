package com.example.crudroomapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditCategoryActivity extends AppCompatActivity {
    private EditText editTextCategoryName;
    private Button btnSave;
    private Repository repository;
    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);

        editTextCategoryName = findViewById(R.id.editTextCategoryName);
        btnSave = findViewById(R.id.btnSave);
        repository = new Repository(getApplication());

        categoryId = getIntent().getIntExtra("categoryId", -1);
        repository.getCategoryById(categoryId).observe(this, category -> {
            if (category != null) {
                editTextCategoryName.setText(category.getCategoryName());
            }
        });

        btnSave.setOnClickListener(v -> {
            String newName = editTextCategoryName.getText().toString();

            if (!newName.isEmpty()) {
                Category category = new Category(newName);
                category.setId(categoryId);  // 🔥 Set ID sebelum update
                repository.updateCategory(category);
                finish();
            }
        });

    }
}

