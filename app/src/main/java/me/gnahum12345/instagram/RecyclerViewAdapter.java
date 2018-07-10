package me.gnahum12345.instagram;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseException;

import java.util.List;

import me.gnahum12345.instagram.model.Post;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    // Tweets array in the constructor
    // for each row, inflate the layout and cache references into viewholder

    private List<Post> mPostList;
    private Context mContext;
    public RecyclerViewAdapter(List<Post> posts) {
        mPostList = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflate = LayoutInflater.from(mContext);
        Log.d("RecyclerViewAdapterTag", "ON CREATE VIEW HOLDER ");

        View tweetView = inflate.inflate(R.layout.post_item, parent, false);
        return new ViewHolder(tweetView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = mPostList.get(position);

        //TODO: Add image to profile picture. (check if it exists)

        try {
            Glide.with(mContext)
                    .load(post.getImage().getFile())
                    .into(holder.ivCoverImage);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvCaption.setText(post.getDescription());
        holder.tvUserName.setText(post.getUser().getUsername());
        //TODO: Scale the heart to be not soooo big.
        holder.btnHeart.setBackgroundResource(R.drawable.ufi_heart_icon);
        holder.btnHeart.setText("");
        Log.d("RecyclerViewAdapterTag", "ON BIND VIEW HOLDER ");
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivCoverImage;
        public TextView tvUserName;
        public TextView tvCaption;
        public Button btnHeart;
        public Button ivProfilePic;

        public ViewHolder(View itemView) {
            super(itemView);

            ivProfilePic = itemView.findViewById(R.id.userProfile);
            ivCoverImage = itemView.findViewById(R.id.ivCoverImage);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            btnHeart = itemView.findViewById(R.id.btnHeart);
        }
    }
}