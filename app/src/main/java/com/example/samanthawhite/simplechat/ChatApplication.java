package com.example.samanthawhite.simplechat;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ChatApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        //required intiialization logic
        ParseObject.registerSubclass(Message.class);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //can be levle.Basic, level.Headers, or level.BOdy
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);

        //set applicationID and server based on the values in the Heroku settings
        //any network interceptors must be added with the configuration builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("simplechat-client")
                .clientBuilder(builder)
                .server("https://codepath-chat-lab.herokuapp.com/parse/").build());

    }
}
