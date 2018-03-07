package com.example.rashmi.resourcefeature;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class PdfCourseMaterials extends Fragment {

    private List<Pdf> pdfList = new ArrayList<>();
    private RecyclerView pdfRecyclerView;
    private PdfAdapter pdfAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pdf_course_materials, container, false);

        Button btnGoBack = v.findViewById(R.id.goback);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.frameQlab,new QLab());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        pdfRecyclerView = v.findViewById(R.id.rvPdf);
        pdfAdapter = new PdfAdapter(getActivity(),pdfList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        pdfRecyclerView.setLayoutManager(mLayoutManager);
        pdfRecyclerView.setItemAnimator(new DefaultItemAnimator());
        pdfRecyclerView.setAdapter(pdfAdapter);
        preparePdfData();
        return v;
    }

    private void preparePdfData() {

        String pdfURL = "https://www.google.com";

        int i;
        i=getArguments().getInt("key");

        switch (i){

            case 0: {
                Pdf pdf = new Pdf("Android Learning", "A book to learn Android",pdfURL);
                pdfList.add(pdf);
                pdf = new Pdf("Java Learning", "A book to learn Java",pdfURL);
                pdfList.add(pdf);
                pdf = new Pdf("Kotlin Learning", "A book to learn Kotlin",pdfURL);
                pdfList.add(pdf);
                pdf = new Pdf("Android Beginner Learning", "A book to learn Android for beginners",pdfURL);
                pdfList.add(pdf);
                pdfAdapter.notifyDataSetChanged();
            }
            break;
            case 1: {
                Pdf pdf = new Pdf("iOS Learning", "A book to learn iOS",pdfURL);
                pdfList.add(pdf);
                pdf = new Pdf("Java Learning", "A book to learn Java",pdfURL);
                pdfList.add(pdf);
                pdf = new Pdf("Swift Learning", "A book to learn Swift",pdfURL);
                pdfList.add(pdf);
                pdfAdapter.notifyDataSetChanged();
            }
            break;
            case 2: {
                Pdf pdf = new Pdf("Big Data Engineer Learning", "A book to learn Big Data Engineer",pdfURL);
                pdfList.add(pdf);
                pdf = new Pdf("Java Learning", "A book to learn Java",pdfURL);
                pdfList.add(pdf);
                pdf = new Pdf("Big Data Engineer Beginner Learning", "A book to learn Big Data for beginners",pdfURL);
                pdfList.add(pdf);
                pdfAdapter.notifyDataSetChanged();
            }
            break;
            case 3: {
                Pdf pdf = new Pdf("Big Data Admin Learning", "A book to learn Big Data Admin concepts",pdfURL);
                pdfList.add(pdf);
                pdf = new Pdf("Java Learning", "A book to learn Java",pdfURL);
                pdfList.add(pdf);
                pdf = new Pdf("Tools Learning", "A book to learn Big Data Tools",pdfURL);
                pdfList.add(pdf);
                pdf = new Pdf("Big Data Beginner Learning", "A book to learn Big Data for beginners",pdfURL);
                pdfList.add(pdf);
                pdfAdapter.notifyDataSetChanged();
            }
            break;
            case 4: {
                Pdf pdf = new Pdf("Cryptocurrency Learning", "A book to learn all about Cryptocurrency and Blockchain",pdfURL);
                pdfList.add(pdf);
                pdf = new Pdf("Java Learning", "A book to learn Java",pdfURL);
                pdfList.add(pdf);
                pdf = new Pdf("Blockchain Beginner Learning", "A book to learn Blockchain for beginners",pdfURL);
                pdfList.add(pdf);
                pdfAdapter.notifyDataSetChanged();
            }
            break;
            case 5: {
                Pdf pdf = new Pdf("Visualisation Learning", "A book to learn Android",pdfURL);
                pdfList.add(pdf);
                pdf = new Pdf("Java Learning", "A book to learn Java",pdfURL);
                pdfList.add(pdf);
                pdf = new Pdf("Data Scientist Learning", "A book to learn Data Scientist for beginners",pdfURL);
                pdfList.add(pdf);
                pdfAdapter.notifyDataSetChanged();
            }
            break;
        }
    }

}
