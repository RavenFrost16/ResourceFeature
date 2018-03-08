package com.example.rashmi.resourcefeature;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class PdfLinks extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String result,btn;

    List<String> pdflist = new ArrayList<String>();

    ArrayList<PdfDataProvider> arrayList = new ArrayList<PdfDataProvider>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video_course_materials, container, false);

        if(isOnline()) {
            btn = getArguments().getString("btnClicked");
            getPdfList();
            recyclerView = v.findViewById(R.id.rv_pdf);

            for (String pdf : pdflist) {
                PdfDataProvider dataProvider = new PdfDataProvider(pdf);
                arrayList.add(dataProvider);
            }

            adapter = new PdfAdapter(arrayList, btn);

            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
        else
        {
            Snackbar mySnackbar =Snackbar.make(v.findViewById(R.id.linear_layout_links),"No Internet Connection",Snackbar.LENGTH_SHORT);
            mySnackbar.show();

        }

        return v;
    }



    public void getPdfList() {
        try {
            PfdLinksBackground backgroundTask = new PfdLinksBackground();
            try {
//                Bundle extras = getIntent().getExtras();
//                String btn = extras.getString("btnClicked");
                result = backgroundTask.execute(btn).get();
            } catch (Exception e) {
                Log.e("log_tag", "Error Parsing Data " + e.toString());
            }
            JSONArray ja = new JSONArray(result);
            for (int i = 2; i < ja.length(); i++) {
                    pdflist.add(ja.get(i).toString());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
