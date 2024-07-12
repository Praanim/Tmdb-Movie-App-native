package com.example.apicallexample;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    // base url
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private  static final  String AUTH_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZDk1N2QzZjhhZDVkNDBjNDhkNWVhYzMyODEwZTkzZiIsIm5iZiI6MTcyMDc3MjEzNi4wMjQ3MDgsInN1YiI6IjYyYjY5NjMwZTFmYWVkMDA2MTBkNDQxNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zEZ5JeM8KwJbYkZHxiTvAW09qZBs1-KV_kzOyThmmXc";


    //retrofit instance
    private static Retrofit retrofit = null;

    // public method for getting retrofit client
    static public Retrofit getClient(){
        if(retrofit == null){
            // Create an interceptor to add authorization header
            Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Authorization", "Bearer " + AUTH_TOKEN)
                            .method(original.method(),original.body())
                            .build();

                    return  chain.proceed(request);
                }
            };

            // create an ok http client
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }


}
