package practical4;

import java.io.Serializable;

public class ServerInfo implements Serializable {
    private String day;
    private String time;
    private String date;

    public ServerInfo(String day, String time, String date) {
        this.day = day;
        this.time = time;
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Day: " + day + ", Time: " + time + ", Date: " + date;
    }
}

