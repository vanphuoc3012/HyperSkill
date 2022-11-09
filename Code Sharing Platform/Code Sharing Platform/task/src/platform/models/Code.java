package platform.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Code {
    private String code;
    private String date;
    private String time;

    public Code(String code) {
        this.code = code;
        this.date = LocalDate.now().toString();
        this.time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public Code() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return this.date +" "+ this.time;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public void setTime(String time) {
        this.time = time;
    }

}
