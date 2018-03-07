package com.example.rashmi.resourcefeature;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RASHMI on 04-03-2018.
 */

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.MyViewHolder> {

    private List<Pdf> pdfList;
    private Activity context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView pdfName,pdfDesc;
        public RelativeLayout pdfMain;

        public MyViewHolder(View view){
        super(view);
            pdfName = view.findViewById(R.id.tvPdfName);
            pdfDesc = view.findViewById(R.id.tvPdfDesc);
            pdfMain = view.findViewById(R.id.pdfMain);
        }
    }

    public PdfAdapter(Activity context, List<Pdf> pdfList){
        this.context = context;
        this.pdfList = pdfList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pdf_list,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Pdf pdf = pdfList.get(position);
        holder.pdfName.setText(pdf.getPdfName());
        holder.pdfDesc.setText(pdf.getPdfDesc());
        holder.pdfMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("url", pdf.getPdfUrl());
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
        return pdfList.size();
    }
}
