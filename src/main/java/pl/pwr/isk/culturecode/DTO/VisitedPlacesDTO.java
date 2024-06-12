package pl.pwr.isk.culturecode.DTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VisitedPlacesDTO {

    private String user_id;
    private String place_id;
    private String timeStamp;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss");
        this.timeStamp = String.valueOf(format.format(LocalDateTime.now()));
    }
}
