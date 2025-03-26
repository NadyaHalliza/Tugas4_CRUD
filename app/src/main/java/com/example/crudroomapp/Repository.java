package com.example.crudroomapp;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final CategoryDao categoryDao;
    private final ProductDao productDao;
    private final ExecutorService executorService;

    public Repository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        categoryDao = db.categoryDao();
        productDao = db.productDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    // CRUD untuk Category
    public void insertCategory(Category category) {
        executorService.execute(() -> categoryDao.insert(category));
    }

    public LiveData<List<Category>> getAllCategories() {
        return categoryDao.getAllCategories();
    }


    // CRUD untuk Product
    public void insertProduct(Product product) {
        executorService.execute(() -> productDao.insert(product));
    }

    public void deleteCategory(int id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            categoryDao.deleteById(id);
        });
    }

    public void updateCategory(Category category) {
        AppDatabase.databaseWriteExecutor.execute(() -> categoryDao.update(category));
    }

    public LiveData<Category> getCategoryById(int id) {
        return categoryDao.getCategoryById(id);
    }


    public List<Product> getProductsByCategory(int categoryId) {
        return productDao.getProductsByCategory(categoryId);
    }
}
