package com.truongpq.englishcommunication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.truongpq.englishcommunication.R;
import com.truongpq.englishcommunication.models.Video;

import java.util.List;

/**
 * Created by TruongPQ on 5/6/16.
 */
public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder>{

    private List<Video> videos;

    private static OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public VideosAdapter(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View videoView = inflater.inflate(R.layout.item_video, parent, false);

        ViewHolder viewHolder = new ViewHolder(videoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Video video = videos.get(position);

        TextView tvTitle = holder.tvTitle;
        tvTitle.setText(video.getTitle());
        TextView tvDecription = holder.tvDecription;
        tvDecription.setText(video.getDecription());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvDecription;

        public ViewHolder(final View itemView) {

            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.title);
            tvDecription = (TextView) itemView.findViewById(R.id.decription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(itemView, getLayoutPosition());
                    }
                }
            });
        }
    }


}
