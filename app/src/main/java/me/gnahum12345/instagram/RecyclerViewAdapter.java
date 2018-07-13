package me.gnahum12345.instagram;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.parse.ParseException;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;

import java.util.List;

import me.gnahum12345.instagram.model.Post;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Post post = mPostList.get(position);

        //TODO: Add image to profile picture. (check if it exists)
//        Log.d("AdapterTAG", Boolean.toString(post.getUser().getParseFile("profilePic") == null));

        holder.ivHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "I liked it");
                if (!holder.liked) {
                    post.like();
                }  else {
                    post.setLike(post.getLikes() - 1);
                }
                post.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (!holder.liked) {
                            holder.liked = true;
                            if (e == null) {
                                Glide.with(mContext)
                                        .load(R.drawable.ufi_heart_active)
                                        .into(holder.ivHeart);
                            } else {
                                Toast.makeText(mContext, "Can't like.. There is a problem", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        } else {
                            holder.liked = false;

                            if (e == null) {
                                Glide.with(mContext)
                                        .load(R.drawable.ufi_heart)
                                        .into(holder.ivHeart);
                            } else {
                                Toast.makeText(mContext, "Can't like.. There is a problem", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                        holder.tvLikes.setText(post.getLikes() + " Likes");
                    }
                });

            }
        });
        holder.tvLikes.setText(post.getLikes() + " Likes");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailActivity.class);
                i.putExtra("post", post);
                mContext.startActivity(i);
            }
        });


        try {
            if (post.getUser() != null) {
                if (post.getUser().getParseFile("profilePic") != null) {
                    Glide.with(mContext)
                            .load(post.getUser().getParseFile("profilePic").getFile())
                            .apply(RequestOptions.placeholderOf(R.drawable.contact_placeholder)
                                    .error(R.drawable.contact_placeholder)
                                    .fitCenter())
                            .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(25, 0)))
                            .into(holder.ivProfilePic);
                }
            } else {
                Glide.with(mContext)
                        .load(R.drawable.contact_placeholder)
                        .into(holder.ivProfilePic);
            }

            Glide.with(mContext)
                    .load(post.getImage().getFile())
                    .into(holder.ivCoverImage);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvCaption.setText(post.getDescription());
//        holder.tvUserName.setText("username that rocks!");
        holder.tvUserName.setText(post.getUser().getUsername());
        Picasso.get()
                .load(R.drawable.ufi_heart)
                .resize(100, 100)
                .centerCrop()
                .into(holder.ivHeart);
//        holder.btnHeart.setBackgroundResource(R.drawable.ufi_heart);
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
        public ImageView ivHeart;
        public ImageView ivProfilePic;
        private TextView tvLikes;
        private boolean liked;
        public ViewHolder(View itemView) {
            super(itemView);

            ivProfilePic = itemView.findViewById(R.id.userProfile);
            ivCoverImage = itemView.findViewById(R.id.ivCoverImage);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            ivHeart = itemView.findViewById(R.id.ivHeart);
            tvLikes = itemView.findViewById(R.id.tvLikes);
            liked = false;
        }

    }

    // Clean all elements of the recycler
    public void clear() {
        mPostList.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        mPostList.addAll(list);
        notifyDataSetChanged();
    }
}