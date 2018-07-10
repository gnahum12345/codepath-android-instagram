package me.gnahum12345.instagram.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@ParseClassName("User")
public class User extends ParseUser {

    private static final String KEY_EMAIL = "email";
    private static final String KEY_PROFILE_PIC = "profilePic";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_USERNAME = "username";


    public String getEmail() {
        return getString(KEY_EMAIL);
    }

    public void setEmail(String email) {
        put(KEY_EMAIL, email);
    }


    public ParseFile getImage() {
        return getParseFile(KEY_PROFILE_PIC);
    }

    public void setImage(ParseFile img) {
        put(KEY_PROFILE_PIC, img);
    }

    public void setPassword(String password) {
        put(KEY_PASSWORD, password);
    }

    public String getUsername() {
        return getString(KEY_USERNAME);
    }

    public void setUsername(String username) {
        put(KEY_USERNAME, username);
    }

}
