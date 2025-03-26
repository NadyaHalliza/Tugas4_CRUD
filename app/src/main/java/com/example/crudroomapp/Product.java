package com.example.crudroomapp;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "products",
        foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "categoryId")
)
public class Product {

        @PrimaryKey(autoGenerate = true)
        public int id;

        public String productName;
        public double price;
        public int categoryId;

        public Product(String productName, double price, int categoryId) {
            this.productName = productName;
            this.price = price;
            this.categoryId = categoryId;
        }
}
