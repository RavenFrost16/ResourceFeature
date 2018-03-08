package com.example.rashmi.resourcefeature;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViewPdf extends AppCompatActivity {
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pdf);
        Intent intent=getIntent();
        String pdfname= intent.getStringExtra("pdfname");
        String category=intent.getStringExtra("category");
        pdfView=findViewById(R.id.viewPdf);
        new RetrivePDFBytes().execute("http://192.168.43.171/"+category+"/"+pdfname);
    }

    class RetrivePDFBytes extends AsyncTask<String,Void,byte[]>{
        ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(ViewPdf.this, "", "Loading...\nPlease wait...", true);

        }
        @Override
        protected byte[] doInBackground(String... strings) {
            InputStream is=null;
            try{
                URL url=new URL(strings[0]);
                HttpURLConnection huc=(HttpURLConnection)url.openConnection();
                if(huc.getResponseCode()==200)
                {
                    is=new BufferedInputStream(huc.getInputStream());
                }
            }
            catch(IOException e)
            {
                return null;
            }
            try {
                return IOUtils.toByteArray(is);
            }
            catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            mProgressDialog.dismiss();

            pdfView.fromBytes(bytes).load();
        }


    }
}
