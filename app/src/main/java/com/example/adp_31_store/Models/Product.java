package com.example.adp_31_store.Models;

// اینجا لایه مدل است که در آن داده ها آماده سازی میشوند
// حالا میتواند از حافظه دستگاه و یا دیتابیس و یا هر جای دیگری باشد
// مانند مدل که در بکاند استفاده میکردیم
// این یک کلاس مدل است برای گرفتن اطلاعات از api و گذاشتن آن ایتجا
public class Product {
    private int id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String image;

    // در تکنیک زیر از getter استفاده میکنیم
    // این یک روش عالی است
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }
}
