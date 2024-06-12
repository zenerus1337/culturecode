package com.culturecode.main.app.localisations;

public class LocalisationRequest {
    private Integer id;
    private String name;
    private String description;
    private String lat;
    private String lang;
    private String qrCodeHint;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getQrCodeHint() {
        return qrCodeHint;
    }

    public void setQrCodeHint(String qrCodeHint) {
        this.qrCodeHint = qrCodeHint;
    }
}
