package pl.pwr.isk.culturecode.model;

import jakarta.persistence.*;

@Entity
@Table(name = "places")
public class PlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = true)
    private String description;
    @Column(unique = true, nullable = false)
    private String qr_code_hint;
    @Column(unique = false, nullable = false)
    private String lat;
    @Column(unique = false, nullable = false)
    private String lang;
    @Column(unique = true, nullable = false)
    private String qrCode;


    public PlaceEntity() {
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getQr_code_hint() {
        return qr_code_hint;
    }

    public void setQr_code_hint(String qr_code_hint) {
        this.qr_code_hint = qr_code_hint;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDescription() {return description;}

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
