package com.example.rashmi.resourcefeature;

import java.net.URL;

/**
 * Created by RASHMI on 04-03-2018.
 */

public class Pdf {
    private String pdfName, pdfDesc, pdfUrl;

    public Pdf(){}

    public Pdf(String pdfName, String pdfDesc, String pdfUrl){
        this.pdfName = pdfName;
        this.pdfDesc = pdfDesc;
        this.pdfUrl = pdfUrl;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public String getPdfDesc() {
        return pdfDesc;
    }

    public void setPdfDesc(String pdfDesc) {
        this.pdfDesc = pdfDesc;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
}
