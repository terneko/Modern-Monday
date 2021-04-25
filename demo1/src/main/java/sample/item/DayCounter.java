package sample.item;

import com.google.gson.JsonObject;
import javafx.scene.control.DatePicker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DayCounter {
    private String title;
    private LocalDate dayEnd;
    private String description;
    private LocalDate now = LocalDate.now();
    private String imageFilePath;
    private long dayLeft;
    private FileWriter file;

    public DayCounter(String title,LocalDate  dayEnd, String description) {
        this(title,dayEnd,description,"");
    }

    public String getTitle() {
        return title;
    }

    public DayCounter(String title, LocalDate  dayEnd, String description, String imageFilePath ) {
        this.title = title;
        this.dayEnd = dayEnd;
        this.description = description;
        this.imageFilePath = imageFilePath;
        this.dayLeft = calculationDayLeft();
    }

    public long getDayLeft() {
        return dayLeft;
    }

    public long calculationDayLeft() {
        long diff = 0;
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String inputString1 = String.valueOf(now);
        String inputString2 = String.valueOf(dayEnd);
        System.out.println(inputString1);
        System.out.println(inputString2);
        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 = myFormat.parse(inputString2);
            diff = date2.getTime() - date1.getTime(); //chrono unit
            diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); // day unit

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(diff);
        return diff;
    }

    public void saveJSON() {
        JSONArray noteArray = openJSON();
        JSONObject obj = new JSONObject();
        obj.put("Title", title);
        obj.put("Description", description);
        obj.put("DayEnd", String.valueOf(dayEnd));
        obj.put("ImagePath",imageFilePath);
        obj.put("DayLeft",dayLeft);
        noteArray.add(obj);
        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("FileNote/dayCounterNote.json");
            file.write(noteArray.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONArray openJSON() {
        JSONParser parser = new JSONParser();
        JSONArray noteArray = null;
        try {
            Object object = parser.parse(new FileReader("FileNote/dayCounterNote.json"));
            noteArray = (JSONArray) object;
            for (int i = 0; i < noteArray.size(); i++) {
                System.out.println(noteArray.get(i));
                JSONObject noteObject = (JSONObject) noteArray.get(i);
                String title = (String) noteObject.get("Title");
                System.out.println(title);
                String description = (String) noteObject.get("Description");
                System.out.println(description);
                String dayEnd = (String) noteObject.get("End");
                System.out.println(dayEnd);
            }
        } catch (org.json.simple.parser.ParseException | IOException e) {
            e.printStackTrace();
        }
        return noteArray;
    }

    public void deleteJSON(JsonObject object) {
        JSONArray noteArray = openJSON();
        for (int i = 0; i < noteArray.size(); i++) {
            if (noteArray.get(i).equals(object)) {
                noteArray.remove(i);
            }
        }
        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("FileNote/dayCounterNote.json");
            file.write(noteArray.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LocalDate getDayEnd() {
        return dayEnd;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getNow() {
        return now;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public FileWriter getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "DayCounter{" +
                "title='" + title + '\'' +
                ", dayEnd=" + dayEnd +
                ", description='" + description + '\'' +
                ", now=" + now +
                ", imageFilePath='" + imageFilePath + '\'' +
                ", dayLeft='" + dayLeft + '\'' +
                '}';
    }
}