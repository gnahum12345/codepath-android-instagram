package me.gnahum12345.instagram;

import android.net.ParseException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
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

            Glide.with(this)
                    .load(p.getUser().getParseFile("profilePic").getFile())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).fitCenter())
                    .into(ivProfilePic);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }
        tvTime.setText(p.getCreatedAt().toString());
        tvCaption.setText(p.getDescription());

    }
}