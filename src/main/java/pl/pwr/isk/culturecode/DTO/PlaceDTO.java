package pl.pwr.isk.culturecode.DTO;

public class PlaceDTO {
    private String name;
    private String description;
    private String qrCodeHint;
    private String lat;
    private String lang;
    private String qrCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}
    public void setLat(String lat) {this.lat = lat;}
    public void setLang(String lang) {this.lang = lang;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQrCodeHint() {
        return qrCodeHint;
    }

    public void setQrCodeHint(String qrCodeHint) {
        this.qrCodeHint = qrCodeHint;
    }

    public String getLat() {return lat;}

    public String getLang() {return lang;}

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

}

