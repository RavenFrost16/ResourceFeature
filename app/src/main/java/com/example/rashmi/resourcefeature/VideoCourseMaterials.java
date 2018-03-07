package com.example.rashmi.resourcefeature;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class VideoCourseMaterials extends Fragment {

    private List<Video> videoList = new ArrayList<>();
    private RecyclerView videoRecyclerView;
    private VideoAdapter videoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_video_course_materials, container, false);

        Button btnGoBack = v.findViewById(R.id.goback);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.frameQlab, new QLab());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        videoRecyclerView = v.findViewById(R.id.rvVideo);
        videoAdapter = new VideoAdapter(getActivity(), videoList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        videoRecyclerView.setLayoutManager(mLayoutManager);
        videoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        videoRecyclerView.setAdapter(videoAdapter);
        prepareVideoData();
        return v;
    }

    public String extractYoutubeId(String url) {
        String query = null;
        try {
            query = new URL(url).getQuery();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String[] param = new String[0];
        try {
            param = query.split("&");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String id = null;
        for (String row : param) {
            String[] param1 = row.split("=");
            if (param1[0].equals("v")) {
                id = param1[1];
            }
        }
        return id;
    }

    private void prepareVideoData() {

        //case 0
        String videoURL = "http://www.youtube.com";
        String videoURL_android = "https://www.youtube.com/watch?v=imwEvQFg384";
        String thumbnail_android = "http://img.youtube.com/vi/" +
                extractYoutubeId("https://www.youtube.com/watch?v=imwEvQFg384") + "/0.jpg";

        String videoURL_java = "https://www.youtube.com/watch?v=3u1fu6f8Hto";
        String thumbnail_java = "http://img.youtube.com/vi/" +
                extractYoutubeId("https://www.youtube.com/watch?v=3u1fu6f8Hto") + "/0.jpg";

        String videoURL_kotlin = "https://www.youtube.com/watch?v=0isrdp5meyQ";
        String thumbnail_kotlin = "http://img.youtube.com/vi/" +
                extractYoutubeId("https://www.youtube.com/watch?v=0isrdp5meyQ") + "/0.jpg";

        String videoURL_androidbegin = "https://www.youtube.com/watch?v=-nz-zwfhrLg";
        String thumbnail_androidbegin = "http://img.youtube.com/vi/" +
                extractYoutubeId("https://www.youtube.com/watch?v=nz-zwfhrLg") + "/0.jpg";

        //case 1
        String videoURL_iOS = "https://www.youtube.com/watch?v=UGbZtMiu3_c";
        String thumbnail_iOS = "http://img.youtube.com/vi/" +
                extractYoutubeId("https://www.youtube.com/watch?v=UGbZtMiu3_c") + "/0.jpg";
        String videoURL_iosSwift = "https://www.youtube.com/watch?v=2OZ07fklur8";
        String thumbnail_iosSwift = "http://img.youtube.com/vi/" +
                extractYoutubeId("https://www.youtube.com/watch?v=2OZ07fklur8") + "/0.jpg";

        //case 2
        String videoURL_bigdata = "https://www.youtube.com/watch?v=A02SRdyoshM";
        String thumbnail_bigdata = "http://img.youtube.com/vi/" +
                extractYoutubeId("https://www.youtube.com/watch?v=A02SRdyoshM") + "/0.jpg";
        String videoURL_bigdata2 = "https://www.youtube.com/watch?v=d0coIjRJ2qQ";
        String thumbnail_bigdata2 = "http://img.youtube.com/vi/" +
                extractYoutubeId("https://www.youtube.com/watch?v=d0coIjRJ2qQ") + "/0.jpg";

        //case 3
        String videoURL_admin = "https://www.youtube.com/watch?v=nm_37WEx0kg";
        String thumbnail_admin = "http://img.youtube.com/vi/" +
                extractYoutubeId("https://www.youtube.com/watch?v=nm_37WEx0kg") + "/0.jpg";
        String videoURL_admin2 = "https://www.youtube.com/watch?v=7bs6bknjA_g";
        String thumbnail_admin2 = "http://img.youtube.com/vi/" +
                extractYoutubeId("https://www.youtube.com/watch?v=7bs6bknjA_g") + "/0.jpg";
        String videoURL_admin3 = "https://www.youtube.com/watch?v=MDTgnbiXotg";
        String thumbnail_admin3 = "http://img.youtube.com/vi/" +
                extractYoutubeId("https://www.youtube.com/watch?v=MDTgnbiXotg") + "/0.jpg";

        //case 4
        String videoURL_blockchain = "https://www.youtube.com/watch?v=jKYhLpHJv8U";
        String thumbnail_blockchain = "http://img.youtube.com/vi/" +
                extractYoutubeId("https://www.youtube.com/watch?v=jKYhLpHJv8U") + "/0.jpg";

        //case 5
        String videoURL_datascience = "https://www.youtube.com/watch?v=Up6KLx3m2ww";
        String thumbnail_datascience = "http://img.youtube.com/vi/" +
                extractYoutubeId("https://www.youtube.com/watch?v=UGbZtMiu3_c") + "/0.jpg";
        String videoURL_datascience2 = "https://www.youtube.com/watch?v=BfowBtIxNu4";
        String thumbnail_datascience2 = "http://img.youtube.com/vi/" +
                extractYoutubeId("https://www.youtube.com/watch?v=BfowBtIxNu4") + "/0.jpg";

        int i;
        i = getArguments().getInt("key");

        switch (i) {

            case 0: {
                Video video = new Video("Android Learning",
                        "A video to learn Android fundamentals by Hitesh Choudhary", videoURL_android, thumbnail_android);
                videoList.add(video);
                video = new Video("Java Learning",
                        "A video to learn Java complete", videoURL_java, thumbnail_java);
                videoList.add(video);
                video = new Video("Kotlin Learning",
                        "A video to learn Kotlin", videoURL_kotlin, thumbnail_kotlin);
                videoList.add(video);
                video = new Video("Android Beginner Learning",
                        "A video to learn Android for beginners",
                        videoURL_androidbegin, thumbnail_android);
                videoList.add(video);
                videoAdapter.notifyDataSetChanged();
            }
            break;
            case 1: {
                Video video = new Video("iOS Learning",
                        "A video to learn iOS", videoURL_iOS, thumbnail_iOS);
                videoList.add(video);
                video = new Video("Swift Learning",
                        "A video to learn Swift", videoURL_iosSwift, thumbnail_iosSwift);
                videoList.add(video);
                videoAdapter.notifyDataSetChanged();
            }
            break;
            case 2: {
                Video video = new Video("Big Data Engineer Learning",
                        "A video to learn Big Data Engineer", videoURL_bigdata, thumbnail_bigdata);
                videoList.add(video);
                video = new Video("Java Learning",
                        "A video to learn Java complete", videoURL_java, thumbnail_java);
                videoList.add(video);
                video = new Video("Big Data Engineer Beginner Learning",
                        "A video to learn Big Data for beginners", videoURL_bigdata2, thumbnail_bigdata2);
                videoList.add(video);
                videoAdapter.notifyDataSetChanged();
            }
            break;
            case 3: {
                Video video = new Video("Big Data Admin Learning",
                        "A video to learn Big Data Admin concepts", videoURL_admin, thumbnail_admin);
                videoList.add(video);
                video = new Video("Tools Learning",
                        "A video to learn Big Data Tools", videoURL_admin2, thumbnail_admin2);
                videoList.add(video);
                video = new Video("Big Data Beginner Learning",
                        "A video to learn Big Data for beginners", videoURL_admin3, thumbnail_admin3);
                videoList.add(video);
                video = new Video("Java Learning",
                        "A video to learn Java", videoURL_java, thumbnail_java);
                videoList.add(video);
                videoAdapter.notifyDataSetChanged();
            }
            break;
            case 4: {
                Video video = new Video("Java Learning",
                        "A video to learn Java", videoURL_java, thumbnail_java);
                videoList.add(video);
                video = new Video("Blockchain Beginner Learning",
                        "A video to learn Blockchain for beginners by Edureka", videoURL_blockchain, thumbnail_blockchain);
                videoList.add(video);
                videoAdapter.notifyDataSetChanged();
            }
            break;
            case 5: {
                Video video = new Video("Visualisation Learning",
                        "A video to learn Visualisation techniques", videoURL_datascience, thumbnail_datascience);
                videoList.add(video);
                video = new Video("Java Learning",
                        "A video to learn Java", videoURL_java, thumbnail_java);
                videoList.add(video);
                video = new Video("Data Scientist Learning",
                        "A video to learn Data Scientist for beginners", videoURL_datascience2, thumbnail_datascience2);
                videoList.add(video);
                videoAdapter.notifyDataSetChanged();
            }
            break;
        }

    }
}
