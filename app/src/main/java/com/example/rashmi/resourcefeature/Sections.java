package com.example.rashmi.resourcefeature;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Sections extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_sections, container, false);

        Button btnPdf = v.findViewById(R.id.btnPdf);
        Button btnVideo = v.findViewById(R.id.btnVideo);
        btnPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i=getArguments().getInt("key");
                final Bundle b = new Bundle();
                b.putInt("key",i);

                PdfCourseMaterials pdfCourseMaterials = new PdfCourseMaterials();
                pdfCourseMaterials.setArguments(b);
                FragmentManager fm = getFragmentManager();
                int back=fm.getBackStackEntryCount();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.frameQlab,pdfCourseMaterials);
                if(back>0){
                    boolean done = getFragmentManager().popBackStackImmediate();
                }
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i=getArguments().getInt("key");
                final Bundle b = new Bundle();
                b.putInt("key",i);

                VideoCourseMaterials videoCourseMaterials = new VideoCourseMaterials();
                videoCourseMaterials.setArguments(b);
                FragmentManager fm = getFragmentManager();
                int back=fm.getBackStackEntryCount();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.frameQlab,videoCourseMaterials);
                if(back>0){
                    boolean done = getFragmentManager().popBackStackImmediate();
                }
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return v;
    }

}
