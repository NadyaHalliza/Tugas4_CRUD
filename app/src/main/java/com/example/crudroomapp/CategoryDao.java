package com.example.crudroomapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    // **Tambahkan Query untuk Delete by ID**
    @Query("DELETE FROM categories WHERE id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM categories ORDER BY id ASC")
    LiveData<List<Category>> getAllCategories();

    @Query("SELECT * FROM categories WHERE id = :id LIMIT 1")
    LiveData<Category> getCategoryById(int id);

}
