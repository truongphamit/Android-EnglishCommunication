package com.truongpq.englishcommunication.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.truongpq.englishcommunication.DividerItemDecoration;
import com.truongpq.englishcommunication.R;
import com.truongpq.englishcommunication.adapters.VideosAdapter;
import com.truongpq.englishcommunication.models.Video;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnlineFragment extends Fragment {

    public static final String link = "http://youtubeinmp4.com/redirect.php?video=";

    private Firebase ref;

    private RecyclerView rvVideos;
    private VideosAdapter adapter;
    private List<Video> videos;
    private RotateLoading listLoading;

    private VideoView videoView;
    private TextView tvVideoView;
    private FrameLayout frameVideo;
    private RotateLoading videoLoading;

    public static OnlineFragment newInstance() {
        OnlineFragment fragment = new OnlineFragment();
        return fragment;
    }

    public OnlineFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_online, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ref = new Firebase("https://truongphamit-video.firebaseio.com/english-video");
        init();
    }

    private void init() {
        videoView = (VideoView) getActivity().findViewById(R.id.videoView);
        tvVideoView = (TextView) getActivity().findViewById(R.id.tv_videoView);
        frameVideo = (FrameLayout) getActivity().findViewById(R.id.frame_video);
        videoLoading = (RotateLoading) getActivity().findViewById(R.id.video_loading);

        rvVideos = (RecyclerView) getActivity().findViewById(R.id.rvVideos);
        videos = new ArrayList<>();
        adapter = new VideosAdapter(videos);
        rvVideos.setAdapter(adapter);
        rvVideos.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        rvVideos.addItemDecoration(itemDecoration);

        listLoading = (RotateLoading) getActivity().findViewById(R.id.list_loading);
        listLoading.start();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                videos.clear();
                for (DataSnapshot videoSnapshot : dataSnapshot.getChildren()) {
                    Video video = videoSnapshot.getValue(Video.class);
                    videos.add(video);
                }
                adapter.notifyDataSetChanged();
                listLoading.stop();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        adapter.setOnItemClickListener(new VideosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                frameVideo.setVisibility(View.VISIBLE);
                videoLoading.start();
                tvVideoView.setText(videos.get(position).getTitle() + ": " + videos.get(position).getDecription());
                videoView.setVideoPath(link + videos.get(position).getId());
                MediaController mediaController = new MediaController(getActivity());
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);
                videoView.requestFocus();
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        videoLoading.stop();
                        videoView.start();
                    }
                });
            }
        });
    }
}
