package com.example.adp_31_store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adp_31_store.Models.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Route router;
    private TextView textViewResult;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        getProducts();
    }

    private void init() {
        textViewResult = findViewById(R.id.text_view_result);
//        imageView = findViewById(R.id.image_view);

        Gson gson = new GsonBuilder().serializeNulls().create();

        // این کلا برای شما لاگ میاندازد
        // در خواست های http را
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request newRequest = originalRequest.newBuilder()
                                .header("interceptor-Header", "xyz")
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .addInterceptor(loggingInterceptor)
                .build();

        // اینجا برای اتصال استفاده شده است
        // همچنین از دیزاین پترن
        // بیلدر استفاده شده است
        // و متد چینینگ
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        // اینجا یک اتصال ساخته شده است
        router = retrofit.create(Route.class);
    }

    private void getProducts(){
        // اینجا بوسیله آبجکت ساخته شده از اینترفیس روت میتوان
        // به متدهای آن دسترسی داشت و متد مورد نظر را اینجا صدا زد
        Call<List<Product>> call = router.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(!response.isSuccessful()) {
                    textViewResult.setText("Code :" + response.code());
                    return;
                }

                List<Product> products = response.body();

                for(Product product: products) {
                    String content = "";
                    content += "ID: " + product.getId() + "\n";
                    content += "Title: " + product.getTitle() + "\n";
                    content += "Price: " + product.getPrice() + "\n";
                    content += "Description: " + product.getDescription() + "\n";
                    content += "Category: " + product.getCategory() + "\n";
                    content += "Image: " + product.getImage() + "\n\n";
//                    Picasso.with(getApplicationContext()).load(product.getImage()).into(imageView);

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}