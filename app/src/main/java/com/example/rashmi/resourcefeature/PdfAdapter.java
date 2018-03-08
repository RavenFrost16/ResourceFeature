package com.example.rashmi.resourcefeature;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shruti on 3/5/2018.
 */

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.RecyclerViewHolder> implements ActivityCompat.OnRequestPermissionsResultCallback{
    private ArrayList<PdfDataProvider> arrayList=new ArrayList<PdfDataProvider>();
    Context c;
    boolean connectivityStatus;
    String pdfClicked,category;

    public PdfAdapter(ArrayList<PdfDataProvider> arrayList,String category) {
        this.arrayList = arrayList;
        this.category=category;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pdf_item,parent,false);
        c=parent.getContext();
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final String pdfName;
        PdfDataProvider pdfDataProvider= arrayList.get(position);
        pdfName=pdfDataProvider.getPdfname();
        holder.tvpdfName.setText(pdfName);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pdfClicked=pdfName;
                viewPDF(pdfName);
            }
        });
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pdfClicked=pdfName;
                downloadPDF(pdfName);
            }
        });
    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    public static  class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView tvpdfName;
        Button view,download;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvpdfName=itemView.findViewById(R.id.tv_pdf_name);
            view=itemView.findViewById(R.id.btn_view);
            download=itemView.findViewById(R.id.btn_download);
        }
    }


    public void viewPDF(String s){
        //go to next activity where a pdf is shown
        connectivityStatus=isOnline();
        if(connectivityStatus) {
            Intent intent = new Intent(c, ViewPdf.class);
            intent.putExtra("category",category);
            intent.putExtra("pdfname",s);
            c.startActivity(intent);
        }
        else
            Toast.makeText(c,"Check Your Network Connection",Toast.LENGTH_SHORT).show();
    }

    public void downloadPDF(String s){
        connectivityStatus=isOnline();
        if(connectivityStatus) {
            //1st Check whether permissions are granted or not
            if (ActivityCompat.checkSelfPermission(c, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                //if permissions not granted then ask for permissions
                ActivityCompat.requestPermissions((Activity) c,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                //above stmt calls onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults)
            }
            else
            {
                DownloadManager dm = (DownloadManager) c.getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("http://192.168.43.171/"+category+"/"+s);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                request.setTitle(s);
                request.setDescription("Downloading");
                //request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"Balguru C++.pdf");//stores in android/app_package/data
                request.setDestinationInExternalPublicDir("/pdfApp/PDFs", s);
                final Long downloadId = dm.enqueue(request);//enqueue required to start downloading

                DownloadManager.Query downloadQuery = new DownloadManager.Query();
                //set the query filter to our previously Enqueued download
                downloadQuery.setFilterById(downloadId);

                //Query the download manager about downloads that have been requested.
                Cursor cursor = dm.query(downloadQuery);
                if(cursor.moveToFirst()) {
                    DownloadStatus(cursor);
                }

                //For toast when download is complete
                BroadcastReceiver downloadReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        //check if the broadcast message is for our enqueued download
                        long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                        if(referenceId == downloadId) {
                            Toast.makeText(c,"File Downloaded in pdfApp/PDFs folder", Toast.LENGTH_LONG).show();
                        }
                    }
                };

                IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
                c.registerReceiver(downloadReceiver, filter);
            }
        }
        else
            Toast.makeText(c,"Check Your Network Connection",Toast.LENGTH_SHORT).show();
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults)
    {
        if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            downloadPDF(pdfClicked);
        else
            Toast.makeText(c,"Storage permission denied",Toast.LENGTH_SHORT).show();
    }

    private void DownloadStatus(Cursor cursor){
        //column for download  status

        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
        int status = cursor.getInt(columnIndex);
        //column for reason code if the download failed or paused

        int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
        int reason = cursor.getInt(columnReason);

        String statusText = "";
        String reasonText = "";

        switch(status){

            case DownloadManager.STATUS_FAILED:
                statusText = "Failed: ";
                switch(reason){
                    case DownloadManager.ERROR_CANNOT_RESUME://Value of COLUMN_REASON when some possibly transient error occurred but we can't resume the download.
                        reasonText = "Please Try Again";
                        break;
                    case DownloadManager.ERROR_DEVICE_NOT_FOUND:
                        reasonText = "Storage Not Found"; //no external storage device was found.
                        break;
                    case DownloadManager.ERROR_FILE_ALREADY_EXISTS:
                        reasonText = "File Already Exist";
                        break;
                    case DownloadManager.ERROR_FILE_ERROR:
                        reasonText = "File Storage Issue";
                        break;
                    case DownloadManager.ERROR_HTTP_DATA_ERROR://error receiving or processing data occurred at the HTTP level.
                        reasonText = "Please Try Again";
                        break;
                    case DownloadManager.ERROR_INSUFFICIENT_SPACE:
                        reasonText = "Insufficient Storage";
                        break;
                }
                break;
            case DownloadManager.STATUS_PAUSED:
                statusText = "Paused\n";
                switch(reason){
                    case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
                        reasonText = "Paused Queued For WIFI";
                        break;
                    case DownloadManager.PAUSED_UNKNOWN:
                        reasonText = "Paused";
                        break;
                    case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
                        reasonText = "Paused Waiting For Network";
                        break;
                    case DownloadManager.PAUSED_WAITING_TO_RETRY://download is paused because some network error occurred and the download manager is waiting before retrying the request.
                        reasonText = "Some Network Error Occurred";
                        break;
                }
                break;
            case DownloadManager.STATUS_PENDING:
                statusText = "Downloading File...";
                reasonText = "";
                break;
            case DownloadManager.STATUS_SUCCESSFUL:
                statusText = "File Downloaded in pdfApp/PDFs folder";
                reasonText = "";
                break;
        }
        Toast.makeText(c,statusText+reasonText,Toast.LENGTH_LONG).show();
    }


}
