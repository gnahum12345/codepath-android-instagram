package me.gnahum12345.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import me.gnahum12345.instagram.model.Post;
import me.gnahum12345.instagram.model.User;

public class ParseApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        ParseUser.registerSubclass(User.class);
        
        Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("gaby-nahum")
                .clientKey("masta-key")
                .server("http://gabriel-fbu-instagram.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);
    }
}
