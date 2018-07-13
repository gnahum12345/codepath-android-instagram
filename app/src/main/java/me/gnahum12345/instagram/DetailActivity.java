package me.gnahum12345.instagram;

import android.net.ParseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.gnahum12345.instagram.model.Post;

public class DetailActivity extends AppCompatActivity {

    ImageView ivCoverImage;
    TextView tvUserName;
    TextView tvCaption;
    TextView tvTime;
    ImageView ivHeart;
    ImageView ivProfilePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivCoverImage = findViewById(R.id.ivCoverImage);
        ivProfilePic = findViewById(R.id.userProfile);
        ivHeart = findViewById(R.id.ivHeart);

        tvTime = findViewById(R.id.tvTime);
        tvCaption = findViewById(R.id.tvCaption);
        tvUserName = findViewById(R.id.tvUserName);

        Post p = getIntent().getParcelableExtra("post");

        try {

            Glide.with(this)
                    .load(p.getImage().getFile())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).fitCenter())
                    .into(ivCoverImage);

            if (p.getUser().getParseFile("profilePic") != null) {
                Glide.with(this)
                        .load(p.getUser().getParseFile("profilePic").getFile())
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_background)
                                .error(R.drawable.ic_launcher_background).fitCenter())
                        .into(ivProfilePic);
            } else {
                Glide.with(this)
                        .load(R.drawable.contact_placeholder)
                        .into(ivProfilePic);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }

        tvTime.setText(getRelativeTimeAgo(p.getCreatedAt()));
        tvCaption.setText(p.getDescription());
        tvUserName.setText(p.getUser().getUsername());
    }

    public String getRelativeTimeAgo(Date time) {
        Date d = new Date();
        String relativeDate = "";
        relativeDate = DateUtils.getRelativeTimeSpanString(time.getTime(), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS,
                DateUtils.FORMAT_ABBREV_RELATIVE).toString();
        return relativeDate;
    }


}