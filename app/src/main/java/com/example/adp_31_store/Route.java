package com.example.adp_31_store;

import com.example.adp_31_store.Models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Route {

    // گرفتن همه محصولات از api مورد نظر
    @GET("products")
    Call<List<Product>> getProducts();

    // گرفتن یک محصول مشخص بوسیله آی دی آن از api
    @GET("products/{id}")
    Call<List<Product>> getProductById(@Path("id") int id);

    // ارسال محصول به api مورد نظر
    @POST("products")
    Call<Product> createProduct(@Body Product product);

    // آپدیت محصول
    @PATCH("products/{id}")
    Call<Product> updateProduct(@Path("id") int id, @Body Product product);

    // حذف محصول
    @DELETE("products/{id}")
    Call<Void> deleteProduct(@Path("id") int id);
}
