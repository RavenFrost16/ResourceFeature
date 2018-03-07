package com.example.rashmi.resourcefeature;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by RASHMI on 05-03-2018.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private List<Video> videoList;
    private Activity context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView videoName,videoDesc;
        public ImageView videoThumbnail;
        public RelativeLayout videoMain;

        public MyViewHolder(View view){
            super(view);
            videoName = view.findViewById(R.id.tvVideoName);
            videoDesc = view.findViewById(R.id.tvVideoDesc);
            videoThumbnail = view.findViewById(R.id.ivVideoThumbnail);
            videoMain = view.findViewById(R.id.videoMain);
        }
    }

    public VideoAdapter(Activity context, List<Video> videoList){
        this.context = context;
        this.videoList = videoList;
    }

    @Override
    public VideoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_list,parent,false);

        return new VideoAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VideoAdapter.MyViewHolder holder, int position) {
        final Video video = videoList.get(position);
        holder.videoName.setText(video.getVideoName());
        holder.videoDesc.setText(video.getVideoDesc());
        Picasso.with(holder.videoThumbnail.getContext())
                .load(video.getVideoThumbnail())
                .into(holder.videoThumbnail);

        holder.videoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("url", video.getVideoUrl());
                FragWebview fragWebview = new FragWebview();
                fragWebview.setArguments(bundle);

                FragmentManager fm = context.getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.add(R.id.frameQlab,fragWebview);
                transaction.addToBackStack("tag");
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }


}
