package com.culturecode.main.app.connection;

public class SentenceRequest {
    private String qrCode;

    public SentenceRequest(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
