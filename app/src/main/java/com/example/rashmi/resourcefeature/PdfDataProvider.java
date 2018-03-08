package com.example.rashmi.resourcefeature;

import android.util.Log;

/**
 * Created by shruti on 3/5/2018.
 */

public class PdfDataProvider {
    String pdfname;

    public PdfDataProvider(String pdfname) {
        Log.i("**Set pdf name**",""+pdfname);

        this.pdfname = pdfname;
    }

    public String getPdfname() {
        return pdfname;
    }

}
